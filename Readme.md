# 1. Setup Oracle DB Image

Start the service to allow communication into the pod

```
kubectl apply -f dev-oracledb-service.yaml
```

Create the secrets object so it can be used

```
kubectl apply -f dev-oracledb-secrets.yaml
```

Deploy the DB

```
kubectl apply -f dev-oracledb-deployment.yaml
```

Get into the pod with the db

```
kubectl exec -it <oracle-db-pod-name> -- /bin/bash
```

Log into sqlplus using the password we set dev-oracledb-secrets.yaml

```
sqlplus system/MyPassword1234@//xp-oracledb-service:1521/ORCLPDB1
```

```
CREATE TABLE orders (
    order_id      VARCHAR2(50)    NOT NULL,
    order_amount  NUMBER(10,2),
    description   VARCHAR2(255),
    order_date    DATE,
    CONSTRAINT pk_orders PRIMARY KEY (order_id)
);

INSERT INTO orders (order_id, order_amount) VALUES ("1234", 10);
```

