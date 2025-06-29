
//http 비동기통신 요청 axios
//base_url은 동일하기 때문에 상수로 지정
//아바타파일 첨부할 예정(multipart로 전송)
import api from 'axios';

const BASE_URL = "/api/member";
const headers = { "Content-Type": "multipart/form-data" };

export default {
    //1. id중복체크 get요청
    async checkUsername(username) {
        console.log("checkusername call");
        const { data } = await api.get(`${BASE_URL}/checkusername/${username}`);
        console.log('AUTH GET CHECKUSERNAME', data);
        return data;

    },
    //2. 회원정보 post로 전송함.
    async create(member) {
        console.log("create call");

        //폼 객체를 만들어서 

        //폼에 전달할 내용 붙이고
        //post방식으로 요청

        const formData = new FormData();
        formData.append('username', member.username);
        formData.append('email', member.email);
        formData.append('password', member.password);

        if (member.avatar) {
            formData.append('avatar', member.avatar);
        }

        const { data } = await api.post(BASE_URL, formData, headers);

        console.log('AUTH POST: ', data);
        return data;

    }
};