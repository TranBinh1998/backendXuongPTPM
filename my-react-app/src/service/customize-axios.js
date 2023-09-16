import axios from "axios";
import data from "bootstrap/js/src/dom/data";

const instance = axios.create({
    baseURL: 'http://localhost:8080/'
});

// instance.interceptors.response.use(function (response) {
//     return response;
// }, function (error) {
//     console.log("Nhảy vào lỗi");
//     console.log(error);
//     alert(error.message);
//     return Promise.reject(error);
// });

instance.interceptors.response.use(function (response) {
    return response; },
    function (error) {
    return error.response.status === 400 ? Promise.resolve(error.response) : Promise.reject(error); });


export  default instance;