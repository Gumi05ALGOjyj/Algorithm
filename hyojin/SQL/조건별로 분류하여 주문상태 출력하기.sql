SELECT order_id ORDER_ID, product_id PRODUCT_ID, date_format(out_date,'%Y-%m-%d') OUT_DATE,
        CASE 
        WHEN date_format(OUT_DATE,'%Y-%m-%d') <= '2022-05-01'
        THEN '출고완료'
        WHEN OUT_DATE IS NULL
        THEN '출고미정'
        ELSE  '출고대기'
        END "출고여부"
from food_order
