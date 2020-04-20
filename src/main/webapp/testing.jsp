<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 4/20/20
  Time: 3:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="main.css">
    <script>
        const getMoviesToDisplay = (title) => {

            let url = '/tracker_app/services/movies/title=' + title;
            let paramaters = {
                "method": "get"
                ,"mode": "cors"
                ,"headers": {
                    "Content-Type": "application/json"
                }
            };

            fetch(url, paramaters)
                .then(result => {
                    console.log(result);
                    return result.json();
                }).then(result => {
                console.log(result);
                displayMovies(result);
            }).catch(error => {
                console.log(error);
            });

        }

        const displayMovies = (movies) => {
            let movieGrouping = document.querySelector("#movie-grouping");

            for (movie in movies) {
                let movieElement = document.createElement("div");
                let image = document.createElement("img");
                let addButton = document.createElement("button");
                let plusSign = document.createElement("i");

                movieElement.className = "movie";
                image.src = movie.image;
                image.alt = movie.title;
                addButton.setAttribute("onclick", "addMovieToCollection(" + movie.imdbId + ")")
                plusSign.className = "fa fa-plus";

                addButton.appendChild(plusSign);
                movieElement.appendChild(image);
                movieElement.appendChild(addButton);

                movieGrouping.appendChild(movieElement);
            }
        }
    </script>

    <title>Browse Movies</title>
</head>
<body>
<nav>
    <a href="#">Browse Movies</a>
    <a href="#">My Movies</a>
    <a href="#">Watch Later</a>
</nav>

<main>
    <h1>Browse Movies</h1><button onclick="getMoviesToDisplay('Interstellar')">Display Movie</button>
    <section class="movie-grouping">

    </section>
</main>
</body>
</html>