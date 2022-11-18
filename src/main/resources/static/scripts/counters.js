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

    const countersJSON = {};
    countersJSON.readings = [];
    countersJSON.counters = [];
    {
        const counterList = counters.querySelectorAll('tr');
        let rIdx = 0;
        let cIdx = 0;
        for (const counter of counterList) {
            const id = Number(counter.getAttribute('name'));
            const counterReading = counter.querySelector('input[name="counterReading"]').value;
            if (id !== -1) {
                // readings
                countersJSON.readings[rIdx] = {};
                countersJSON.readings[rIdx].id = id;
                if (counterReading === '') {
                    countersJSON.readings.pop();
                    continue;
                }
                countersJSON.readings[rIdx].counterReading = Number(counterReading);
                rIdx++;
            }
            else {
                // counters
                countersJSON.counters[cIdx] = {};
                {
                    const typeId = Number(counter.querySelector('select[name="typeId"]').value);
                    if (typeId === -1) {
                        hasAlert = true;
                        alert('Выберите тип счетчика');
                        countersJSON.counters.pop();
                        continue;
                    }
                    countersJSON.counters[cIdx].typeId = typeId;
                }
                {
                    const number = counter.querySelector('input[name="number"]').value;
                    if (number === '') {
                        hasAlert = true;
                        alert('Введите номер счетчика');
                        countersJSON.counters.pop();
                        continue;
                    }
                    countersJSON.counters[cIdx].number = number;
                }
                if (counterReading === '') {
                    hasAlert = true;
                    alert('Введите текущие показания для нового счетчика');
                    countersJSON.counters.pop();
                    continue;
                }
                countersJSON.counters[cIdx].counterReading = Number(counterReading);
                cIdx++;
            }
        }
    }

    if ((countersJSON.readings.length === 0
        && countersJSON.counters.length === 0)
        || hasAlert) {
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
        if (response.status === 200) {

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