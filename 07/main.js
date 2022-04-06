let boxesEl = document.querySelector('.boxes')
        let totalBox = document.querySelector('.points')
        let btn = document.querySelector('#btn')
        let data = ['#3498db', '#9b59b6', '#e74c3c', '#2c3e50', '#d35400']
        let colors = [...data]
        function renderBox(arr) {
            boxesEl.innerHTML = ''
            for (let i = 0; i < arr.length; i++) {
                boxesEl.innerHTML += `<div class="box" style="background-color:${arr[i]}"
            onclick="removeBox(${i})"><div>`
            }
        }
        function updateTotalBox() {
            totalBox.innerText = colors.length
        }

        function update() {
            renderBox(colors)
            updateTotalBox()
        }

        btn.addEventListener('click', function () {
            colors = [...colors, ...data]
            update()
        })

        function removeBox(index) {
            colors = colors.filter((color, i) => i != index)
            update()
        }

        window.onload = update
