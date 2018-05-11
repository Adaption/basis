window.onload = () => {
    setActiveSidebarItem(0)
}

window.onresize = () => {
    if (document.body.clientWidth > 780) {
        closeSidebar(null, 0);
    }
}

function setActiveSidebarItem(index) {
    for (var i = 0; i < 8; i++) {
        document.getElementById("side-bar-item-" + i).classList.remove("side-bar-item-active");
        document.getElementById("side-bar-item-" + i).classList.add("side-bar-item-hover");
    }
    document.getElementById("side-bar-item-" + index).classList.add("side-bar-item-active");
    document.getElementById("side-bar-item-" + index).classList.remove("side-bar-item-hover");
}

function openSidebar(_, duration) {
    let sidebar = document.getElementById("side-bar");
    let overlay = document.getElementsByClassName("overlay");
    document.getElementById("close-sidebar").style.display = "inline";
    anime({
        targets: sidebar,
        translateX: 250,
        easing: [0.475, 0.445, 0.000, 0.955],
        duration: duration
    });
    anime({
        targets: overlay,
        opacity: 0.5,
        zIndex: 2,
        easing: "linear",
        duration: 0
    });
}

function closeSidebar(_, duration) {
    let sidebar = document.getElementById("side-bar");
    let overlay = document.getElementsByClassName("overlay");
    document.getElementById("close-sidebar").style.display = "none";
    anime({
        targets: sidebar,
        translateX: 0,
        easing: [0.475, 0.445, 0.000, 0.955],
        duration: duration
    });
    anime({
        targets: overlay,
        opacity: 0,
        zIndex: 0,
        easing: "linear",
        duration: 0
    });
}