const API_URL = "/api/comics";

function getComics(page = 0, size = 20){
    fetch(`${API_URL}?page=${page}&size=${size}&sort=title,asc`)
        .then(response => response.json())
        .then(data => {
            const comicList = document.getElementById("comic-list");
            comicList.innerHTML = "";

            data.content.forEach(comic => {
                const comicDiv = document.createElement("div");
                comicDiv.textContent = `${comic.title}`;
                comicList.appendChild(comicDiv);
            });
        })
        .catch(error => console.error("Error:",error));
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