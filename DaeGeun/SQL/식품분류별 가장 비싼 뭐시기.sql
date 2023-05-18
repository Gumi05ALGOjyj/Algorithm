select a.category, a.price, a.product_name
from food_product a, (select category, max(price) mp
                from food_product
                where category in ('과자','국','김치','식용유')
                group by category
                having max(price)) b
where (a.category, a.price) = (b.category, b.mp)
order by 2 desc
;
