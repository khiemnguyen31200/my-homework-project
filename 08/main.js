const btn = document.getElementById('btn');
const image = document.getElementById('image');
const select = document.getElementById('breed-list');
const list = document.querySelector(".list ul");
let ele =[]

// Vừa load trang phải gọi API để render danh sách breed
// API : https://dog.ceo/api/breeds/list/all

async function getBreedList() {
    // Gọi API để lấy danh sách giống loài
    let res = await axios.get("https://dog.ceo/api/breeds/list/all")
    // Sau khi có data thì hiển thị kết quả trên giao diện
    renderBreed(res.data.message)
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
    ele = []
    let res = await axios.get(`https://dog.ceo/api/breed/${select.value}/list`)
    console.log(res.data)
    renderSubBreed(res.data.message)
    if (list.innerText=="") {
        const li = document.createElement("li")
            li.innerText = "None"
            list.insertAdjacentElement("beforeend", li) 
    }
})
function renderSubBreed(breeds) {
    
    for (let i = 0; i < breeds.length; i++) {
            list.innerHTML += `<li onclick="ShowPhoto(${i})">${breeds[i]}</li>`
            ele[i] = breeds[i]
    }
}
async function ShowPhoto(subBreed){
    try {
        let res=await axios.get(`https://dog.ceo/api/breed/${select.value}/${ele[subBreed]}/images/random`) 
        console.log(res)       
        image.src = res.data.message
    } catch (error) {
        console.log(error.response.data.message)
    }   
}

