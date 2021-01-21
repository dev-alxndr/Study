import {useEffect, useState} from 'react';

export default function useMounted() {
    const [mounted, setMounted] = useState(false);

    useEffect(() => {
        setMounted(true);
    }, []);
    return mounted;
}

/*
RULES

* 하나의 컴포넌트에서 훅을 호출하는 순서는 항상 같아야한다.
* 훅은 함수형 혹은 컴포넌트 또는 커스텀 훅 안에서만 호출되어야 한다.
*
* if, for, 함수안에서 훅을 호출해서는 안된다.
* */