const API_URL = "/api/comics";

function getComics(page = 0, size = 5){
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
                document.getElementById("comic-form").reset();
            })
            .catch(error => {
                console.error("Error:", error)
                alert(error.message);
            });
        });
}

addComic();