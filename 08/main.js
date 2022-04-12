const btn = document.getElementById('btn');
const image = document.getElementById('image');
const result= document.querySelector('body > div.result')
const select = document.getElementById('breed-list');
const list = document.querySelector(".list ul");
let elements =[]


// Vừa load trang phải gọi API để render danh sách breed
// API : https://dog.ceo/api/breeds/list/all

async function getBreedList() {
    try {
        let res = await axios.get("https://dog.ceo/api/breeds/list/all")
        renderBreed(res.data.message)
    } catch (error) {
        console.log(error.response.data.message)
    }   
}
function renderBreed(breeds) {
    for (const breed in breeds) {
        if (Object.hasOwnProperty.call(breeds, breed)) {
            const option = document.createElement("option")
            option.innerText = breed
            select.insertAdjacentElement("beforeend", option)
        }
    }
}
getBreedList()
btn.addEventListener("click", async function () {
    list.innerHTML = ""
    elements = []
    let res = await axios.get(`https://dog.ceo/api/breed/${select.value}/list`)
    renderSubBreed(res.data.message)
    if (list.innerText=="") {
            const li = document.createElement("li")
            li.innerText = "None"
            list.insertAdjacentElement("beforeend", li) 
            result.style.display="none"
    }else {
            image.src=""
            result.style.display="block" 
    }
})
function renderSubBreed(breeds) {
    for (let i = 0; i < breeds.length; i++) {
            list.innerHTML += `<li onclick="ShowPhoto(${i})">${breeds[i]}</li>`
            elements[i] = breeds[i]
    }
}
async function ShowPhoto(subBreed){
    try {
        let res=await axios.get(`https://dog.ceo/api/breed/${select.value}/${elements[subBreed]}/images/random`)       
        image.src = res.data.message
    } catch (error) {
        console.log(error.response.data.message)
    }   
}

