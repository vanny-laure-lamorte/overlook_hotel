// Script for handling display of login modal and tab navigation
document.addEventListener("DOMContentLoaded", function () {
    const loginButton = document.getElementById("loginButton");
    const loginModal = document.getElementById("loginModal");
    const closeModal = document.getElementById("closeModal");
    const tabs = document.querySelectorAll(".tab");
    const tabContents = document.querySelectorAll(".tab-content");

    loginButton.addEventListener("click", () => {
        loginModal.classList.remove("hidden");
        loginModal.classList.add("visible");
    });

    closeModal.addEventListener("click", () => {
        loginModal.classList.remove("visible");
        loginModal.classList.add("hidden");
    });

    tabs.forEach((tab) => {
        tab.addEventListener("click", function () {
            tabs.forEach((t) => t.classList.remove("active"));
            tabContents.forEach((c) => c.classList.remove("active"));

            this.classList.add("active");
            const selected = this.getAttribute("data-tab");
            document.getElementById(selected + "Tab").classList.add("active");
        });
    });
});