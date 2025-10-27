# **java-racingcar-precourse**

# **구현할 기능 목록**

---

## **Controller**

### **1. RaceController**

> 게임의 진행 흐름을 제어하는 객체
>

- [x]  **게임 실행 흐름 제어**
    - [x]  `run()`메서드로 전체 게임 흐름 관리
    - [x]  사용자 입력 → 파싱 → 서비스 호출 → 결과 출력 순서 보장
- [x]  **클라이언트한테 차량 이름 받아서 race 만들도록 하기**
    - [x]  `view.requestCarNames()`호출로 사용자 입력 요청
    - [x]  `parser.parseCarListRequest()`호출로 입력 파싱
    - [x]  `raceService.createRace()`호출로 레이스 생성
- [x]  **클라이언트한테 시도 횟수 받아서 경기 진행시키기**
    - [x]  `view.requestNumberOfAttempts()`호출로 시도 횟수 입력 요청
    - [x]  `parser.parseNumberOfAttempts()`호출로 입력 파싱
    - [x]  `raceService.playRace()`호출로 경기 진행
- [x]  **결과 출력 처리**
    - [x]  `view.showRaceExecutionResult()`호출로 경기 과정 출력
    - [x]  `view.showResult()`호출로 최종 우승자 출력

### **2. Parser**

> 원시 문자열 입력을 분석하고, 유효성 검사를 수행하며, 이를 서비스 계층의 메서드 인자로 사용될 수 있는 구조화된 데이터 전달 객체
>

- [x]  **차 이름 문장을 구분자 기준으로 분리해주는 기능**
    - [x]  `parseCarListRequest(String line)`메서드 구현
    - [x]  쉼표(`,`) 구분자로 문자열 분리
    - [x]  `validateInputIsPresent()`메서드로 null, blank 검증
- [x]  **구분자 기준으로 공백을 제거해준 후 차량 이름만 담긴 List로 변환**
    - [x]  `String.trim()`사용으로 앞뒤 공백 제거
    - [x]  `filter(name -> !name.isEmpty())`사용으로 빈 문자열 제거
    - [x]  구분자 사이에 공백만 있을 경우 예외 처리
    - [x]  `validateCarNames()`메서드로 빈 목록 검증
- [x]  **String으로 받은 시도 횟수를 int로 변경해주는 기능**
    - [x]  `parseNumberOfAttempts(String clientMessage)`메서드 구현
    - [x]  `Integer.parseInt()`사용으로 문자열을 정수로 변환
    - [x]  숫자가 아니면 예외를 던진다 (`NumberFormatException`처리)
    - [x]  최소 시도 횟수보다 작으면 예외를 던진다 (0 이하 거부)

---

## **Service**

### **1. RaceService**

> 레이스 관련 비즈니스 로직을 처리하는 서비스 계층
>

- [x]  **레이스 생성 기능**
- [x]  **레이스 진행 기능**
    - [x]  `playRace(Race race, int attempts)`메서드 구현
    - [x]  지정된 횟수만큼 레이스 라운드 진행
    - [x]  각 라운드마다`RaceExecutionResultDto`생성 및 저장
- [x]  **우승자 결정 기능**
    - [x]  `getWinners(Race race)`메서드 구현
    - [x]  우승자 이름 목록을`String`리스트로 반환

---

## **Domain**

### **1. Race (도메인)**

> 레이스의 핵심 비즈니스 로직을 담당하는 도메인 객체
>

- [x]  **레이스 생성 및 검증**
    - [x]  `startWith(List<Car> carList)`팩토리 메서드 구현
    - [x]  자동차 이름 중복 검증 (`validateCarNamesDuplication()`)
    - [x]  `Set`을 사용한 중복 검사 로직
- [x]  **자동차 이동 처리**
    - [x]  `moveCarsIfPossible(MoveStrategy moveStrategy)`메서드 구현
    - [x]  전략 패턴을 통한 이동 조건 확인
    - [x]  이동 가능한 자동차만 전진 처리
- [x]  **현재 상태 조회**
    - [x]  `getCurrentStatue()`메서드로 현재 자동차 상태 반환
    - [x]  `CarStatusDto`리스트로 변환하여 반환
- [x]  **우승자 결정**
    - [x]  `getWinners()`메서드로 최대 거리 자동차들 반환
    - [x]  `Stream`API를 사용한 최대 거리 계산
    - [x]  최대 거리와 같은 자동차들을 우승자로 선정

### **2. Car (도메인)**

> 자동차의 상태와 행동을 관리하는 도메인 객체
>

- [x]  **자동차 생성 및 검증**
    - [x]  `from(String carName)`팩토리 메서드 구현
    - [x]  자동차 이름 길이 검증 (5자 이하)
    - [x]  자동차 이름 빈 문자열 검증
    - [x]  초기 거리 0으로 설정
- [x]  **자동차 이동 기능**
    - [x]  `advance()`메서드로 1칸 전진
    - [x]  불변 객체 패턴으로`Distance`객체 재생성
- [x]  **상태 조회 기능**
    - [x]  `getStatus()`메서드로`CarStatusDto`반환
    - [x]  `getName()`메서드로 자동차 이름 반환
    - [x]  `getDistance()`메서드로 현재 거리 반환
    - [x]  `isAtMaxDistance()`메서드로 최대 거리 여부 확인

### **3. Distance (값 객체)**

> 거리 값을 표현하는 불변 값 객체
>

- [x]  **거리 값 관리**
    - [x]  `Distance(Integer distance)`생성자 구현
    - [x]  `getDistance()`메서드로 거리 값 반환
    - [x]  불변 객체로 구현하여 값 변경 방지
- [x] **음수 값 예외 처리**
    -[x] `validNegativeDistance()`을 통한 음수 값 예외 처리

### **4. MoveStrategy (전략 패턴)**

> 자동차 이동 조건을 결정하는 전략 인터페이스
>

- [x]  **전략 인터페이스 정의**
    - [x]  `MoveStrategy`인터페이스 정의
    - [x]  `isMovable()`메서드로 이동 가능 여부 반환
- [x]  **랜덤 이동 전략 구현**
    - [x]  `RandomMoveStrategy`클래스 구현
    - [x]  0~9 범위의 랜덤 값 생성
    - [x]  4 이상일 때 이동 가능하도록 구현

---

## **Presentation**

### **1. View (프레젠테이션 계층)**

> 사용자와의 입출력을 담당하는 프레젠테이션 계층
>

- [x]  **사용자 입력 처리**
    - [x]  `requestCarNames()`메서드로 자동차 이름 입력 요청
    - [x]  `requestNumberOfAttempts()`메서드로 시도 횟수 입력 요청
    - [x]  `Input`인터페이스를 통한 입력 처리
- [x]  **결과 출력 처리**
    - [x]  `showRaceExecutionResult()`메서드로 경기 과정 출력
    - [x]  `showResult()`메서드로 최종 우승자 출력
    - [x]  `RaceFormatter`를 통한 포맷팅 처리

### **2. RaceFormatter (포맷팅)**

> 레이스 결과를 사용자에게 보여줄 형태로 포맷팅하는 클래스
>

- [x]  **레이스 실행 결과 포맷팅**
    - [x]  `format(List<RaceExecutionResultDto>)`메서드 구현
    - [x]  상수를 사용한 포맷팅 문자열 관리
    - [x]  거리 표현을 문자로 시각화
- [x]  **우승자 포맷팅**
    - [x]  `formatWinner(List<String>)`메서드 구현
    - [x]  쉼표로 구분된 우승자 목록 포맷팅

### **3. ConsoleInput/ConsoleOutput (입출력)**

> 콘솔을 통한 입출력을 처리하는 클래스들
>

- [x]  **콘솔 입력 처리**
    - [x]  `ConsoleInput`클래스로`Input`인터페이스 구현
    - [x]  `Console.readLine()`사용으로 사용자 입력 받기
- [x]  **콘솔 출력 처리**
    - [x]  `ConsoleOutput`클래스로 출력 메시지 관리
    - [x]  상수를 사용한 메시지 문자열 관리
    - [x]  `System.out.println()`사용으로 결과 출력

---

## **Common**

### **1. AppConfig: 의존성 관리 객체**

> 클래스간의 의존성을 주입해주고, 관리하는 객체
>

- [x]  **싱글톤 패턴 구현**
    - [x]  `getInstance()`메서드로 단일 인스턴스 반환
    - [x]  `private`생성자로 외부 인스턴스화 방지
- [x]  **모든 클래스의 의존성 주입**
    - [x]  각 계층별 객체 생성 및 연결

### **2. ErrorMessage: 예외 메시지를 관리하는 enum**

> 애플리케이션 전반의 에러 메시지를 관리하는 열거형
>

- [x]  **에러 메시지 정의**
    - [x]  `CAR_NAME_TOO_LONG`: 자동차 이름 길이 초과
    - [x]  `CAR_NAME_EMPTY`: 자동차 이름 빈 문자열
    - [x]  `INVALID_ATTEMPT_COUNT`: 잘못된 시도 횟수
    - [x]  `DUPLICATE_CAR_NAME`: 중복된 자동차 이름
    - [x]  `INPUT_CANNOT_BE_BLANK`: 빈 입력값
- [x]  **에러 메시지 포맷팅**
    - [x]  `format(Object... args)`메서드로 동적 메시지 생성
    - [x]  `toString()`메서드로 기본 메시지 반환