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

#### 카테고리 별 도서 판매량 집계하기
```sql
SELECT b.category CATEGORY, sum(bs.sales) TOTAL_SALES
from book b join book_sales bs
on b.book_id = bs.book_id
where bs.sales_date like "2022-01%"
group by b.CATEGORY
order by b.CATEGORY;
```

#### 조건별로 분류하여 주문상태 출력하기
```sql
SELECT ORDER_ID, PRODUCT_ID, date_format(OUT_DATE,'%Y-%m-%d') OUT_DATE, case
 when OUT_DATE>'2022-05-01' then '출고대기'
 when out_date is null then '출고미정'
else '출고완료'
end as 출고여부
from food_order
order by order_id;
```
#### 조건에 맞는 사용자와 총 거래금액 조회하기
```sql

```
