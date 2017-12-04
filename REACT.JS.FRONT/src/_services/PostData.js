export function PostData(endpoint, data) {
    let ROOTURL = 'http://localhost:8080/';

    // Parametars binding
    // Noob pristup ali radi
    let params = "";
    for (var property in data) params += property + "=" + data[property] + "&";

    return new Promise((resolve, reject) => {
        fetch(ROOTURL + endpoint, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: params
        })
        .then((response) => response.json())
        .then((res) => {
            resolve(res);
        })
        .catch((error) => {
            alert(error);
            reject(error);
        });
    });
}

export function GetData(endpoint, data) {
    let ROOTURL = 'http://localhost:9000/api/';

    let params = "";
    for (var property in data) params += property + "=" + data[property] + "&";

    return new Promise((resolve, reject) => {
        fetch(ROOTURL + endpoint, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: params
        })
        .then((response) => response.json())
        .then((res) => {
            resolve(res);
        })
        .catch((error) => {
            alert(error);
            reject(error);
        });
    });
}
