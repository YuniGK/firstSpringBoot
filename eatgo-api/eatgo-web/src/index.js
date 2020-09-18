(async () => {
    const url = 'http://localhost:8080/restaurants';

    const response = await fetch(url);

    const restaurants = await response.json();

    const element = document.getElementById('app');

    /* #app에 아래의 내용을 추가해준다.
    *  JSON.stringify - json 타입을 String형으로 변경해준다. */
    element.innerText = `
        ${restaurants.map(restaurants => `
        
            <p>
                ${restaurants.id}
                ${restaurants.name}
                ${restaurants.address}  
            </p>
        
        `).join('')}
    `;
    //JSON.stringify(restaurants);

    console.log(restaurants);
})();