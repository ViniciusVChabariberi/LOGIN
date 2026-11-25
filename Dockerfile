# A compilacao (GraalVM) acontece no GitHub Actions; aqui so se empacota o executavel nativo.
FROM debian:bookworm-slim

RUN apt-get update \
    && apt-get install -y --no-install-recommends ca-certificates \
    && rm -rf /var/lib/apt/lists/* \
    && useradd --system --no-create-home login

WORKDIR /app
COPY spring/target/login /app/login

USER login
EXPOSE 8090
ENTRYPOINT ["/app/login"]
