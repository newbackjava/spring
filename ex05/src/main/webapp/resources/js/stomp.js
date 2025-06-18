// StompJs.Client 객체 생성
const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/chat-app'
});

// 연결 성공시 콜백
// 구독 토픽 등록
stompClient.onConnect = (frame) => {
    console.log(frame)
    setConnected(true);
    // 구독 토픽 등록 및 수신 처리 핸들러 등록
    // 토픽 문자열: '/topic/greetings' - 입장 메시지
    stompClient.subscribe('/topic/greetings', (greeting) => {
        console.log('/topic/greetings', greeting.body)
        showMessage(JSON.parse(greeting.body).name + '님이 입장했습니다.');
    });
    // 토픽 문자열: '/topic/chat' - chat 메시지 
    stompClient.subscribe('/topic/chat', (chat) => {
        console.log('/topic/chat', chat.body)
        const message = JSON.parse(chat.body);
        showMessage(`${message.name}:${message.content}`);
    });
    
    // 연결 성공시 입장 메시지 보내기
    const name =  document.getElementById('name').value;
    stompClient.publish({
        destination: '/app/hello',
        body: JSON.stringify({name})
    });
    
};

// 웹 소켓 에러 발생시 콜백
stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

// Stomp 에러 발생시 콜백
stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

// 연결됬을 때 엘리먼트 프로퍼티, 스타일 변경
function setConnected(connected) {
    const connectBtn = document.getElementById('connect');
    const disconnectBtn = document.getElementById('disconnect');
    const messages = document.getElementById('chat-messages');

    connectBtn.disabled = connected;
    disconnectBtn.disabled = !connected;
    messages.innerHTML = '';
}


// 연결하기
function connect() {
    stompClient.activate();
}

// 연결 끊기
function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log('Disconnected');
}


// 메시지 전송하기
function sendMessage() {
    const name =  document.getElementById('name').value;
    const content =  document.getElementById('content').value;
    console.log({name, content})
    stompClient.publish({
        destination: '/app/chat',
        body: JSON.stringify({name, content})
    });

}

// 수신 메시지 출력하기
function showMessage(message) {
    const messages = document.getElementById('chat-messages');
    messages.innerHTML += '<tr><td>' + message + '</td></tr>'

}

// 이벤트 핸들러 설정
window.addEventListener("DOMContentLoaded", (event) => {
    const forms = document.querySelectorAll('.form-inline');
    const connectBtn = document.getElementById('connect');
    const disconnectBtn = document.getElementById('disconnect');
    const sendBtn = document.getElementById('send');

    connectBtn.addEventListener('click', () => connect());
    disconnectBtn.addEventListener('click',  () => disconnect());
    sendBtn.addEventListener('click', () => sendMessage());
    for(const form of forms) {
        console.log(form)
        form.addEventListener('submit', (e) => e.preventDefault());
    }
});
