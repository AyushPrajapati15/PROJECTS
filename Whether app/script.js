let user_input = document.querySelector("input");
let btn = document.querySelector("button");
let icon = document.querySelector(".icon");
let weatherData = document.querySelector(".weather");
let temperature = document.querySelector(".temp");
let description = document.querySelector("desc");

// 62f4367c410104e4584c2ba7755485b2
// https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}




btn.addEventListener('click', () => {
    let city = user_input.value;
    getWeatherData(city)
});

const getWeatherData = async (city) => {
  // try {

  const response = await fetch(
    `https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${`62f4367c410104e4584c2ba7755485b2`}`
  );
  const data = await response.json();
  console.log(data);
  // } catch (error) {
  //     console.error('Error fetching weather data:', error);
  // }

  const weatherIcon = data.weather[0].icon;
  icon.innerHTML = `<img src="http://openweathermap.org/img/wn/${weatherIcon}.png" alt=weather_image" />`;

  const cityname = data.name;
  const countryName = data.sys.country;
  weatherData.innerHTML = `${cityname},${countryName}`;
};

// script.js:35 Uncaught (in promise) TypeError: Cannot set properties of null (setting 'innerHTML')    at getWeatherData (script.js:35:27)
