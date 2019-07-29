FROM hyperledger/iroha:1.0.0
WORKDIR /opt/iroha_data
ENTRYPOINT [""]
COPY run-iroha.sh wait-for-it.sh /
RUN chmod +x /run-iroha.sh; \
    chmod +x /wait-for-it.sh; \
    apt-get install -y jq

CMD ["/run-iroha.sh"]
