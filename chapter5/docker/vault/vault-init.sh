#!/bin/bash

echo "==> 스크립트 실행 중"
echo "==> 서버 dev 모드로 실행"
# vault server 시작 // -config=/vault/config/config.hcl
vault server -dev -dev-root-token-id="myroot" -dev-listen-address="0.0.0.0:8200"&

# 대기 시간 추가 (예: 5초)
echo "==> 서버가 제대로 동작할 때까지 대기 5초"
sleep 5

# Vault 서버에 접근할 주소 설정
export VAULT_ADDR='http://0.0.0.0:8200'

# root 토큰 설정
export VAULT_TOKEN='myroot'

# Vault CLI 명령어를 사용하여 데이터를 메모리에 저장
#echo "==> 저장 경로 생성"
#VAULT_TOKEN=myroot vault secrets enable -version=2 -path=licensing-service kv

# 경로를 새로 생성하는게 잘 안 되서 기존에 있던 secret 경로로 데이터를 생성했습니다
echo "==> default 데이터 저장"
VAULT_TOKEN=myroot vault kv put secret/licensing-service/default @/vault/data/default.json

echo "==> dev 데이터 저장"
VAULT_TOKEN=myroot vault kv put secret/licensing-service/dev @/vault/data/dev.json

# 스크립트 종료까지 서버를 계속 유지
echo "==> Vault 서버를 유지합니다. Ctrl+C로 스크립트를 종료하세요."
wait