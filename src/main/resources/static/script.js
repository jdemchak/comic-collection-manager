const API_URL = "/api/comics";

let page = 0;
const size = 5;
let currentSearch = "";
function getComics(){
    let url = `${API_URL}?page=${page}&size=${size}&sort=title,asc`;

    if (currentSearch) {
        url = `${API_URL}/search?title=${encodeURIComponent(currentSearch)}&page=${page}&size=${size}`;
    }

    fetch(url)
        .then(response => response.json())
        .then(data => {
            const comicList = document.getElementById("comic-list");
            comicList.innerHTML = "";

            data.content.forEach(comic => {
            const comicDiv = document.createElement("div");

            const title = document.createElement("h3");
            title.textContent = comic.title;
            title.classList.add("comic-title");

            const details = document.createElement("div");
            details.classList.add("comic-details");
            details.style.display = "none";
            details.innerHTML = `
                <p><strong>ISBN:</strong> ${comic.isbn}</p>
                <p><strong>Publisher:</strong> ${comic.publisher}</p>
                <p><strong>Format:</strong> ${comic.format}</p>
                <p><strong>Volume:</strong> ${comic.volume}</p>
            `;

            title.addEventListener("click", () => {
                details.style.display =
                    details.style.display === "none" ? "block" : "none";
            });

            comicDiv.appendChild(title);
            comicDiv.appendChild(details);
            comicList.appendChild(comicDiv);
        });

            updatePaginationButtons(data);
        })
        .catch(error => console.error("Error:",error));
}
function nextPage() {
    page++;
    getComics();
}
function prevPage() {
    if (page > 0) {
        page--;
        getComics();
    }
}
function updatePaginationButtons(data) {
    document.getElementById("prev-btn").disabled = page === 0;
    document.getElementById("next-btn").disabled = data.last;
}
function searchComics() {
    const input = document.getElementById("search-title").value.trim();

    currentSearch = input;
    page = 0;
    getComics();
}

function addComic(){
    document.getElementById("comic-form")
        .addEventListener("submit", function(e){
            e.preventDefault();

            const comic = {
                isbn: document.getElementById("isbn").value,
                title: document.getElementById("title").value,
                publisher: document.getElementById("publisher").value,
                format: document.getElementById("format").value,
                volume: document.getElementById("volume").value,
            };

            fetch(API_URL, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(comic)
            })
            .then(response => {
                if(!response.ok){
                    throw new Error("Failure to add comic");
                }
                return response.json();
            })
            .then(() => {
                getComics();
                comicCountByPublisher();
                document.getElementById("comic-form").reset();
            })
            .catch(error => {
                console.error("Error:", error)
                alert(error.message);
            });
        });
}

function deleteComic() {
    const form = document.getElementById("delete-comic-form");

    form.addEventListener("submit", function (event) {
        event.preventDefault();

        const isbn = document.getElementById("delete-isbn").value.trim();

        if (!isbn) {
            alert("Please enter an ISBN");
            return;
        }

        if (!confirm(`Are you sure you want to delete comic with ISBN ${isbn}?`)) {
            return;
        }

        fetch(`${API_URL}/${isbn}`, {
            method: "DELETE"
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Comic not found or could not be deleted");
            }
            alert("Comic deleted successfully");
            form.reset();
            if (page > 0 && document.getElementById("comic-list").children.length === 1) {
                page--;
            }
            getComics();
            comicCountByPublisher();
        })
        .catch(error => {
            console.error("Error:", error);
            alert("Failed to delete comic");
        });
    });
}

function updateComic() {
    const form = document.getElementById("update-comic-form");

    form.addEventListener("submit", function (event) {
        event.preventDefault();

        const isbn = document.getElementById("update-isbn").value.trim();

        if (!isbn) {
            alert("Please enter an ISBN");
            return;
        }

        const comic = {
            isbn: document.getElementById("update-isbn").value,
            title: document.getElementById("update-title").value,
            publisher: document.getElementById("update-publisher").value,
            format: document.getElementById("update-format").value,
            volume: document.getElementById("update-volume").value,
        };

        fetch(`${API_URL}/${isbn}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(comic)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to update comic");
            }
        })
        .then(() => {
            getComics();
            comicCountByPublisher();
            form.reset();
        })
        .catch(error => {
            console.error("Error:", error);
            alert(error.message);
        });
    });
}

function comicCountByPublisher(){
    fetch(`${API_URL}/publisher/count`)
        .then(res => res.json())
        .then(data => {
            const listDiv = document.getElementById("publisher-list");
            listDiv.innerHTML = "";
            for (const [publisher, count] of Object.entries(data)) {
                listDiv.innerHTML += `<p>${publisher}: ${count}</p>`;
            }
        });
}

document.addEventListener("DOMContentLoaded", () => {
    getComics();
    comicCountByPublisher();
    addComic();
    deleteComic();
    updateComic();
});