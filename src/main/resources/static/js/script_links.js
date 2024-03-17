'use strict'

function disableLoginBtn() {
    document.getElementById("loginBtn").style.display = "none"
    document.getElementById("signupBtn").style.marginRight = "80%"
}

function disableSignupBtn() {
    document.getElementById("signupBtn").style.display = "none"
    document.getElementById("loginBtn").style.marginRight = "80%"
}

function disableUploadBtn() {
    document.getElementById("uploadBtn").style.display = "none"
    document.getElementById("logoutBtn").style.marginRight = "71%"
}

function fileValidate() {
    const file     = document.getElementById("image"),
          fileSize = (file.files.item(0).size)/(1024*1024)

    if(fileSize > 2) {
        alert("File Size Must Be Less Than 2 MB")
        return false
    }
    return true
}

function toggleDesc(imgId) {
    const id = 'hideDesc_id' + imgId
    const desc = document.getElementById(id)

    if (desc.style.display === "none") {
        desc.style.display = "block"
    } else {
        desc.style.display = "none"
    }
}

function confirmDelete() {
    const confirmDel = confirm("Are You Sure You Want To Delete This Image??");
    if (confirmDel == true) {
        return true
    } else {
        return false
    }
}
