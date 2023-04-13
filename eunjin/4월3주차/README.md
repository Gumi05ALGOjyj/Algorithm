#### 가격이 제일 비싼 식품의 정보 출력하기
```sql
SELECT * from FOOD_PRODUCT
where price = (select max(price) from FOOD_PRODUCT);
```

#### 가격이 제일 비싼 상품 구하기
```sql
SELECT max(price) as max_price
from product;
```


#### 최댓값 구하기
```sql
SELECT DATETIME as 시간
from ANIMAL_INS
where DATETIME = (select max(datetime) from ANIMAL_INS);
```


#### 최솟값 구하기
```sql
SELECT DATETIME as 시간
from ANIMAL_INS
where DATETIME = (select min(datetime) from ANIMAL_INS);
```


#### 동물 수 구하기
```sql
SELECT count(*) from ANIMAL_INS;;
```

#### 중복 제거하기
```sql
select count(distinct name) from ANIMAL_INS;
```
