[![](https://jitpack.io/v/brinst07/fake-data-generator.svg)](https://jitpack.io/#brinst07/fake-data-generator)
# Fake Data Generator
## 소개
Fake Data Generator는 테스트코드에서 DTO에 가짜 데이터를 자동으로 채워주는 라이브러리입니다.
이 라이브러리는 영어와 한국어를 지원하는 다국어 데이터 생성을 지원하여, 실제 데이터를 기반으로 한 테스트가 가능하게 합니다.
DTO 필드에 어노테이션만 추가하고, 메소드에 DTO 클래스를 전달하는 간단한 방식으로 빠르고 쉽게 사용이 가능합니다.

## 기능
- 다국어 지원 : 영어와 한국어로 가짜 데이터를 생성합니다.
- 간편한 통합 : DTO 필드에 어노테이션만 추가하고, DTO 클래스를 메소드에 전달하면 됩니다.

## 사용법
- build.gradle
```groovy
dependencies {
    testImplementation platform('org.junit:junit-bom:5.10.0')
    testImplementation 'org.junit.jupiter:junit-jupiter'
    implementation 'com.github.brinst07:fake-data-generator:<version>'
}

allprojects {
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io'}
    }
}
```

- 값을 주입 할 DTO에 필드 정의
```java
public class TestDTO {
	@FakeField(type = DataType.NAME)
	private String name;
	@FakeField(type = DataType.FIRST_NAME)
	private String firstName;
	@FakeField(type = DataType.LAST_NAME)
	private String lastName;
	@FakeField(type = DataType.NUMBER, min = 1, max = 100)
	private int age;
	@FakeField(type = DataType.DATE)
	private LocalDate birthday;
	@FakeField(type = DataType.EMAIL)
	private String email;
}
```

- 실제 사용 예제
```java
//DTO만 주입 할 경우 기본값은 한국어
TestDTO generate1 = FakeDataGenerator.generate(TestDTO.class);
//DTO 클래스 외에 언어 타입을 주입하여 다국어 형태로 주입 가능
TestDTO generate2 = FakeDataGenerator.generate(TestDTO.class, LangType.EN);
```

## 기여
기여를 환영합니다! 제안 사항이나 개선 사항이 있으면 리포지토리를 포크하고, 변경 사항을 추가한 후 풀 리퀘스트를 제출해주세요. 변경 사항은 테스트를 충분히 거쳐 기존의 코드 스타일을 따르도록 해주세요.

## 라이선스
이 프로젝트는 MIT 라이선스 하에 제공됩니다. 자세한 내용은 LICENSE 파일을 참조하세요.
