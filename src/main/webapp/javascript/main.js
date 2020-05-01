const displayCollectionModal = (imdbid) => {
    let collectionButtons = document.querySelectorAll(".addToCollectionButton");

    for (button of collectionButtons) {
        button.onclick = () => {addMovieToCollection(imdbid)};
    }
}

const errorMessage = (message) => {
    displayMessage(message, "Error! ", "danger", "#alertBox");
}

const successMessage = (message) => {
    displayMessage(message, "Success! ", "success", "#alertBox");
}

const formErrorMessage = (message) => {
    displayMessage(message, "", "danger", "#formAlertBox");
}

const displayMessage = (message, keyword, bootstrapColor, outputBox) => {
    let alertLocation = document.querySelector(outputBox);
    alertLocation.innerHTML = "";

    let alertBox = document.createElement("div");
    alertBox.classList = "alert alert-" + bootstrapColor + " alert-dismissible fade show";

    let closeButton = document.createElement("button");
    closeButton.classList = "close";
    closeButton.setAttribute("data-dismiss", "alert");
    closeButton.type = "button";
    closeButton.innerHTML = "&times;";

    let boldText = document.createElement("strong");
    boldText.textContent = keyword;

    let errorMessage = document.createElement("span");
    errorMessage.textContent = message;

    alertBox.appendChild(closeButton);
    alertBox.appendChild(boldText);
    alertBox.appendChild(errorMessage);

    alertLocation.appendChild(alertBox);
}