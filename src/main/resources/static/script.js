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