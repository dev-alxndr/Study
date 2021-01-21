

## babel install
```
npm install @babel/core @babel/cli @babel/preset-react 
 ```


 babel로 compile해서 변환하기?

```
npx babel --watch src --out-dir . --presets @babel/preset-react 
```
> --watch 모드 : 변경되면 자동으로 바꿔줌.
> . : 현재 디렉토리에 파일 생성


## Webpack

- 다양한 기능
    - 파일 내용 기반으로 파일 이름에 해시값 추가
    - 사용되지 않는 코드 제거
    - 자바스크립트 압축
    - js에서 css, json 텍스트파일 등을 일반 모듈처럼 불러오기
    - 환경 변수 주입
- 가장 큰 이유 = 모듈 시스템 (ESM, commonJS)을 사용하고 싶어서


# Create-react-app

```bash
npx create-react-app cra-test
```

cra vs next.js
-> 서버사이드 렌더링여부



부모가 렌더링되면 자식도 렌더링된다.
그렇게 안할려면 React.memo 를 사용하자.
```js
import React from 'react';

function Title({title}) {
    return <p>{title}</p>
};

export default React.memo(Title);
```

속성변수는 불변변수
상태값은 변수