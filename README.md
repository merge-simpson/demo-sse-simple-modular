**🇰🇷 Korean** | [English](#download-project)

# 프로젝트 받기

깃이 설치되어 있어야 합니다. ([Git 설치](https://git-scm.com/download/win))  
원하는 경로에서 다음 명령어를 입력하면, 프로젝트가 하위 폴더로 다운로드됩니다.  
터미널에서 경로 이동은 `cd` 명령어를 이용하세요.
([Change Directory](https://www.google.com/search?q=change+directory))

```bash
git clone https://github.com/merge-simpson/demo-sse-springboot3-modular.git
```

# 실행

**필요**

- JDK 21 이상
- IDE가 프로젝트를 관리한다면, 프로젝트 설정에서 언어 수준을 21 이상, 모듈 설정에서 언어 수준을 프로젝트를 따르도록 합니다.
  - (인텔리제이) Project structure > project > Language level: 21
  - (인텔리제이) Project structure > modules > Language level: project default

**정보**

모듈

- 'demo-simple-sse-standalone' 모듈에 있는 SimpleSseApplication을 실행할 수 있습니다.
- Inquiry Service는 따로 뭘 하진 않습니다.

신택스

- WARN: 일부 신택스는 JDK 21 preview에서 사용할 수 있습니다.
  - 프로덕션에는 대안을 택하는 것이 좋습니다.
  - 예를 들어, 21 preview의 `java.lang.STR` 대신 `java.text.MessageFormat`을 사용할 수 있습니다.

---

🇰🇷 [Korean](#프로젝트-받기) | **English**

# Download Project

Make sure you have Git installed: [Install Git](https://git-scm.com/download/win)  
Enter the below command in the desired directory, and the project will be downloaded as a subfolder.  
Use the `cd` command to move the directory in your terminal.
([Change Directory](https://www.google.com/search?q=change+directory))

```bash
git clone https://github.com/merge-simpson/demo-sse-springboot3-modular.git
```

Open project and recognize it as a gradle project in your IDE, and the dependency libraries will be installed.

# Run

**Prerequisites**

- JDK 21 or above
- If the project is managed by IDE, set language level to 21 or above, and modules' follow the project.
  - (intellij) Project structure > project > Language level: 21
  - (intellij) Project structure > modules > Language level: project default

**Information**

Modules

- You can run SimpleSseApplication in "demo-simple-sse-standalone" module.
- Inquiry Service does nothing.

Syntax

- WARN: Some syntaxes are available in the JDK 21 preview.
  - You'd be better off with an alternative for the production.
  - For example, you can use `java.text.MessageFormat` instead of `java.lang.STR` of 21 preview.