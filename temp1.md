# 1주차 - 정리

## 당장 다음달에 이벤트를 진행하려고 한다…
기존 시스템에 가용성을 높이기 위해.. 당신은 무엇을 할 수 있을까...?

### 디비 관점

- 읽기용과 쓰기용을 분리하여 디비 I/O 부하를 줄일수 있을것이다..
- 샤딩을 사용해서 데이터를 분산처리할수 있겠다..
- CQRS 패턴을 적용하여 데이터의 명령과 쿼리를 분리하여 효율을 극대화 한다

### 인프라 관점?

- 스케일업을 하여 운영되고 있는 애플리케이션의 성능을 더 높여줄수 있을것이다
- 로드밸런서를 해주는 역할이 있다고 하면..(L4/L7 스위치.. 웹서버) 스케일 아웃하여 애플리케이션을 더 많이 사용할 것이다

### 애플리케이션 관점

- 부하가 걸리는 요청을 애플리케이션에서 모두 처리하지 않고 메시지로 전송하여 후속처리 한다
- 블럭킹 되어 있는 로직을 논블럭킹으로 전환하여 한정된 자원을 극대화할 수 있도록 운영한다

## 그렇다면 블럭킹과 논블럭킹은 무엇인가..?

- 제어권 여부에 따라 달라질수 있을것 같다
    - 블럭킹 - 블럭킹을 가지고 있다
    - 논블럭킹 - 제어권을 넘겨주었다

## 그럼 동기와 비동기는 무엇이지..?

- 결과를 기다리는 여부에 따라 달라질수 있다
    - 동기 - 요청한 수행 결과를 기다린다
    - 비동기 - 요청만 하고 결과를 기다리지 않는다

# 우리는 왜 코루틴을 배워야 할까…?

## 스레드 전환

- 안드로이드에선 뷰를 그리기 위한 메인 스레드가 필요하다
- 그렇기 때문에 뷰를 그리기 이전에 비즈니스 로직을 수행하기 위해 별도 스레드를 사용하려고 한다

```jsx
fun onCreate() {
	thread {
		val news = getNewsFromApi()
		val sortedNews = news.sortedByDescending { it.publishedAt }
		runOnUiThread {
			view.showNews(sortedNews)
		}
	}
}
```

- 위와 같은 스레드 전환 방식을 아직도 사용하고 있지만, 다음과 같은 문제가 있다
    - 스레드가 실행되었을 때 멈출 수 있는 방법이 없어 메모리 누수로 이어질 수 있다
    - 스레드를 많이 생성하면 비용이 많이 든다
    - 스레드를 자주 전환하면 복잡도를 증가시키며 관리하기도 어렵다
    - 코드가 쓸데없이 길어지고 이해하기 어려워진다

> 그렇다면 스레드 전환을 좀 더 효율적으로 할수 없을까?
> 

## 콜백

- 콜백의 기본적인 방법은 함수를 논블로킹으로 만들고, 함수의 작업이 끝났을 때 호출될 콜백 함수를 넘겨준다

```jsx
fun onCreate() {
	getConfigFromApi() { config -> 
		getNewsFromApi(config) { news -> 
			getUserFromApi() { user -> 
				view.showNews(user,news)
			}
		}
	}
}
```

- 그런데 이는 또 다른 문제를 야기시킨다
    - 콜백 구조로는 두 작업을 동시에 처리할 수 없다
    - 콜백을 중간에 취소하려면 구현하는데 많은 노력이 필요하다
    - 들여쓰기가 많아질수록 가독성이 떨어진다…(콜백지옥☠️☠️)

# RxJava와 리액티브 스트림

- RxJava를 사용한 방법이 콜백을 사용한 것보다 훨씬 더 좋은 방법이다

```jsx
fun showNews() {
	disposables += Observable.zip(
		getConfigFromApi().flatMap { getNewsFromApi(it) },
		getUserFromApi(),
		Function2 { news: List<News>, config: Config ->
			Pair(news, config)
		})
		.subscribeOn(Schedulers.io())
		.observeOn(AndroidSchedulers.mainThread())
		.subscribe { (news, config) ->
			view.showNews(news, config)
		}
}
```

- Rxjava를 사용하면 동시성 처리도 되고 메모리 누수도 없지만, zip, flatMap과 같은 RxJava 함수를 사용해야 하고 값을 Pair로 묶고 분리도 해야해서 러닝커브가 약간 높다고 할수 있다

## 코틀린 코루틴을 사용하면 어떻게 작성될까..?

```jsx
fun onCreate() {
	viewModelScope.launch {
		val config = async { getConfigFromApi() }
		val news = async { getNewsFromApi(config.await()) }
		val user = async { getUserFromApi() }
		view.showNews(user.await(), news.await())
	}
}
```

- 코루틴을 사용하게 되면 기존의 작성된 로직을 크게 바꾸지 않아도 된다
- 그리고 별도의 쓰레드를 사용하기 위해 명시적으로 생성하거나 관리해줄 필요가 없다

## 코틀린 코루틴은 실제로 자원을 효율적으로 사용하는 걸까?

- 스레드 비용 문제는 다음과 같은 상황을 재현해보면 확인할 수 있다

```jsx
fun main() {
	repeat(100_000) {
		thread {
			Thread.sleep(1000L)
			print(".")
		}
	}
}
```

- 위는 스레드를 만들고 1초 동안 잠을 자게 만든다. 이는 상당한 시간이 걸리거나 OutOfMemoryError 예외로 프로그램이 종료되는 걸 볼 수 있다

```jsx
fun main() = runBlocking {
	repeat(100_000) {
		launch {
			delay(1000L)
			print(".")
		}
	}
}
```

- 스레드 대신 코루틴을 사용했으며 스레드를 재우는 대신 코루틴을 중단시킨다. 이렇게 되면 하나의 스레드로 기능을 동작할 수 있다

## 코루틴은 쓰레드와 다르게 자원을 효율적으로 사용하게 되는걸까?

- 코루틴이 쓰레드보다 자원을 효율적으로 관리할 수 있었던 이유는 재개할 수 있는 기능을 제공하기 때문이다
- 코루틴은 중단되면 Continuation이라는 객체를 남기는데 이후 함수가 중단된 시점부터 진행하고 싶으면 Continuation을 이용하여 재개할 수 있게 된다

```jsx
suspend fun main() {
	println("Before")
	suspendCoroutine<Unit> {	}
	println("After")
}
```

- suspendCoroutine 함수는 기본 라이브러리에 제공하는 함수로 코루틴을 중단하는 기능을 제공한다
- 실제 해당 메서드를 실행해보면 “After”는 출력이 되지 않는다
- 그 이유는 중단된 이후에 재개하는 로직이 없기 때문이다

```jsx
suspend fun main() {
	println("Before")
	suspendCoroutine<Unit> { continuation ->
		continuation.resume(Unit)
	}
	println("After")
}
```

- 이와 같이 resume 메서드를 호출하여 중단된 함수를 재개할 수 있게 된다

## 왜 재개할때 Unit을 넘기는걸까?

- 우선 재개할때 데이터를 넘기는 이유는 중단되는 과정에서 데이터를 저장하지 않기 때문이다
- 그래서 재개할때 수행된 데이터를 넘겨주게 되면 별도 데이터 저장없이 재개할때 중단된 지점에서 처리된 데이터를 그대로 사용할 수 있게 된다
- 그러면 왜 Unit을 넘겨야 할까?
- 데이터를 직접 넘겨도 된다. 단, 만약 예외가 발생했을때는 데이터를 넘기지 못하고 예외를 리턴해야 하기 때문에 이를 추상화하기 위해 Unit을 넘기는 것이다
- 아래는 Unit이 아닌 데이터를 넘길 경우 예외처리하는 예시 코드이다

```jsx
// 1. 결과값이 isSuccesful = true일 경우에만 결과값을 재개한다
suspend fun requestUser(): User {
    return suspendCancellableCoroutine<User> { cont ->
        requestUser { resp ->        
            if (resp.isSuccesful) {
                cont.resume(resp.data)
            } else {
                val e = ApiException(
                    resp.code,
                    resp.message
                )
                cont.resumeWithException(e)
            }
        }
    }
}

// 2. 성공일때는 데이터를 넘기고, 실패할 경우에는 Exception을 리턴하여 재개한다
suspend fun requestNews(): News {
    return suspendCancellableCoroutine<User> { cont ->
        requestNews(
            onSuccess = { news -> cont.resume(news) }
            onFailure = { e-> cont.resumeWithException(e) }
        )
    }
}
```

# 코루틴 내부 동작을 살펴보자

## 컨티뉴에이션 전달 방식

- Continuation은 함수에서 함수로 인자를 통해 전달된다

```jsx
suspend fun getUser(): User?
suspend fun setUser(user: User)
suspend fun checkAvailablilty(flight: Flight): Boolean

// 자바 코드로 변환
fun getUser(continuation: Continuation<*>): Any?
fun setUser(user: User, continuation: Continuation<*>): Any
fun checkAvailablilty(flight: Flight, continuation: Continuation<*>): Any
```

- Any 또는 Any?로 바뀌었는데, 그 이유는 중단 함수를 실행하는 도중에 중단되면 선언된 타입의 값을 반환하지 않을 수 있기 때문이다
- 조금 더 살펴보자면 중단 함수는 COROUTINE_SUSPEND라는 객체를 리턴하게 된다
- 다음의 메서드를 통해서 확인해보자

```jsx
suspend fun myFunction() {
	println("Before")
	delay(1000)
	println("After")
}
```

- 내부 로직은 간단하다
- 해당 메서드를 디컴파일하면 아래와 같다

```jsx
fun myFunction(continuation: Continuation<Unit>): Any {
    val continuation = continuation as? MyFunctionContinuation ?: MyFunctionContinuation(continuation)

    if (continuation.label == 0) {
        println("Before")

        continuation.label = 1
        if (delay(1000L, continuation) == COROUTINE_SUSPEND) {
            return COROUTINE_SUSPEND
        }
    }

    if (continuation.label == 1) {
        println("After")
        return Unit
    }

    error("Impossible")
}

public suspend fun delay(timeMillis: Long) {
    if (timeMillis <= 0) return // don't delay
    return suspendCancellableCoroutine sc@ { cont: CancellableContinuation<Unit> ->
        // if timeMillis == Long.MAX_VALUE then just wait forever like awaitCancellation, don't schedule.
        if (timeMillis < Long.MAX_VALUE) {
            cont.context.delay.scheduleResumeAfterDelay(timeMillis, cont)
        }
    }
}
```

- 하나씩 살펴보자

### 참고
- https://blog.naver.com/tgyuu_/223290411871
