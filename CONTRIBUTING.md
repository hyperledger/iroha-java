# Contribution guide

## How to start

1. Fork this repo
2. Select iroha version for dev:
    ```bash
    export IROHA_VERSION=v1.0.0_rc2
    git checkout ${IROHA_VERSION}
    ```
2. Create feature branch:
    ```bash
    git checkout -b feature/add-something-exciting
    ```
3. Make changes
4. Commit changes
5. Create pull request with base branch = upstream `${IROHA_VERSION}`.
6. Be active in discussion in your PR.


## While you're working

1. Make sure you use Google Java Code Style
    - IntelliJ IDEA: https://github.com/soramitsu/did-resolver/blob/develop/intellij-google-codestyle.xml
2. Make sure your code is covered with tests. You can test your code with JUnit (Java) or Spock (Groovy).
3. Do integration testing with [testcontainers](./testcontainers). Changes that are not covered with integration tests will not be accepted.
3. Make sure all analyzers and CI are green in your Pull Request.
