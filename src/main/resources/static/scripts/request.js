function subStatement() {
    let historyStatement = document.getElementById('historyStatement');
    let submitStatement = document.getElementById('submitStatement');
    let submitHref = document.getElementById('submitHref');
    let historyHref = document.getElementById('historyHref');
    submitStatement.style.display = "block";
    historyStatement.style.display = "none";
    submitHref.style.textDecoration = "underline";
    historyHref.style.textDecoration = "none";
}

async function hisStatement() {
    let historyStatement = document.getElementById('historyStatement');
    let submitStatement = document.getElementById('submitStatement');
    let submitHref = document.getElementById('submitHref');
    let historyHref = document.getElementById('historyHref');
    submitStatement.style.display = "none";
    historyStatement.style.display = "block";
    submitHref.style.textDecoration = "none";
    historyHref.style.textDecoration = "underline";
    let url = '/app/history/getAllRequests';
    fetch(url).then(function (response) {
        return response.json();
    }).then(function (data) {
        console.log(data)
        let historyBody = document.getElementById("history");
        historyBody.innerHTML = '';
        for (let i = 0; i < data.length; i++) {
            let tr = document.createElement("tr");
            let td1 = document.createElement("td");
            td1.textContent = data[i].topic;
            let td2 = document.createElement("td");
            td2.textContent = "Новая";
            let td3 = document.createElement("td");
            td3.textContent = data[i].content;
            tr.appendChild(td1)
            tr.appendChild(td2)
            tr.appendChild(td3)
            historyBody.appendChild(tr);
        }
    }).catch(function (error) {
        console.error(error);
        alert(error);
    });
}