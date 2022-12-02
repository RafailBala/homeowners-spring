const request = document.getElementById('username').value;


// Подать
const submitButton = document.getElementById('send_request');
submitButton.addEventListener('click', async () => {
    let hasAlert = false;

    const requestJSON = {};
    requestJSON.email = email;

    

    let url = '/app/requests';
    console.log(`PATCH ${url}`);

    try {
        const response = await fetch(url, {
            method: 'PATCH',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestJSON),
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