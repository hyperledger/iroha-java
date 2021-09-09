#this corresponds to iroha 1.3.0.rc1
FROM ghcr.io/hyperledger/iroha:df8b9258ff546ebf7dcdbe6ca2f52ac82f94922138649fe9fc875558b0c3e715-debug
WORKDIR /opt/iroha_data
ENTRYPOINT [""]
COPY run-iroha.sh wait-for-it.sh /
RUN chmod +x /run-iroha.sh; \
    chmod +x /wait-for-it.sh; \
    apt-get install -y jq

CMD ["/run-iroha.sh"]
