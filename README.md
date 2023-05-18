# BE

# StarCraft Chatting - Clone Coding

> 항해99 14기 11조 클론코딩, <br>
> 스타크래프트 단체 채팅으로 같이 이야기를 나누는 공간입니다.

# ⚙️ BE - Tech Stack
<br>
<div align=center> 
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=JAVA&logoColor=white"> 
<img src="https://img.shields.io/badge/SPRING BOOT-6DB33F?style=for-the-badge&logo=SPRING BOOT&logoColor=white">
<img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white">
<img src="https://img.shields.io/badge/MariaDB-4479A1?style=for-the-badge&logo=mariaDB&logoColor=white">
<img src="https://img.shields.io/badge/redis-DC382D?style=for-the-badge&logo=redis&logoColor=white">
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
<img src="https://img.shields.io/badge/amazonrds-527FFF?style=for-the-badge&logo=amazonrds&logoColor=white">
</div>

<br>

## 💻 11조의 S.A가 궁금하다면?
https://round-roadrunner-698.notion.site/Clone-Project-6aec846876c44a2ea98d0558f8623b31
<br>

## 🧑‍🤝‍🧑 회원 관리 관련 기능
1. 회원 가입<br>
    - memberId, password를 Client에서 전달받습니다.<br>
    - 중복된 ID, 공백으로 된 ID는 사용할 수 없습니다.<br>
      <br>

2. 로그인 / 로그아웃<br>
   - 로그인 성공 시, Access Token을 발급하고 헤더에 토큰을 추가합니다.<br>
   - Redis를 적용하여 로그아웃을 진행합니다.<br>
   <br>

## 💻 채팅 관련 기능
1. 채팅 관련<br>
   - STOMP 프로토콜을 사용하여 채팅을 구현했습니다.<br>
   - 브라우저에서 서버로 메시지를 보내고 서버에서 브라우저로 메시지를 받을 수 있습니다.<br>
   - 브라우저에서 WebSocket을 열고, 브라우저에서 서버로 CONNECT 명령을 보내고 연결을 설정합니다. 그런 다음 브라우저에서 서버로 SEND 명령을 보내 메시지를 보낼 수 있습니다. 서버에서 브라우저로 SUBSCRIBE 명령을 보내 메시지를 받을 수 있습니다.<br>
   - 욕설 필터를 구현했습니다. 채팅방에 입장해서 욕설을 입력하면 "*"로 나타납니다.<br>
