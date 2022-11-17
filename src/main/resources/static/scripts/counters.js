const counters = document.getElementById('counterss');
// Добавить счетчик
const addCounterButton = document.getElementById('addCounter');
addCounterButton.addEventListener('click', () => {
    const counterTemplate = document.getElementById('counter-template');
    const counter = counterTemplate.content.cloneNode(true);
    counters.appendChild(counter);
});
// Подать
const submitButton = document.getElementById('submit');
submitButton.addEventListener('click', async () => {
    let hasAlert = false;

    const countersJSON = [];
    {
        const counterList = counters.querySelectorAll('tr');
        let i = 0;
        for (const counter of counterList) {
            countersJSON[i] = {};
            const id = Number(counter.getAttribute('name'));
            const counterReading = counter.querySelector('input[name="counterReading"]').value;
            if (id !== -1) {
                countersJSON[i].id = id;
            }
            else {
                {
                    const typeId = Number(counter.querySelector('select[name="typeId"]').value);
                    console.log(typeId);
                    if (typeId === -1) {
                        hasAlert = true;
                        alert('Выберите тип счетчика');
                        countersJSON.pop();
                        continue;
                    }
                    countersJSON[i].typeId = typeId;
                }
                {
                    const number = counter.querySelector('input[name="number"]').value;
                    if (number === '') {
                        hasAlert = true;
                        alert('Введите номер счетчика');
                        countersJSON.pop();
                        continue;
                    }
                    countersJSON[i].number = number;
                }
                if (counterReading === '') {
                    hasAlert = true;
                    alert('Введите текущие показания для нового счетчика');
                }
            }
            if (counterReading === '') {
                countersJSON.pop();
                continue;
            }
            countersJSON[i].counterReading = Number(counterReading);
            i++;
        }
    }

    if (countersJSON.length === 0
        | hasAlert) {
        if (!hasAlert) alert('Введите данные для подачи');
        return;
    }

    let url = '/app/counter_readings';
    console.log(`POST ${url}`);

    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(countersJSON),
            cache: "no-store"
        });
        if (response.status === 201) {
            location.reload();
        }
        else{
            const result = await response.json();
            console.log(JSON.stringify(result));
            alert(`${result.status} ${result.error}`);
        }
    } catch (error) {
        console.error(error);
        alert(error);
    }
});