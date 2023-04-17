-- 카테고리 별
-- 도서 판매량

select category CATEGORY, sum(sales) TOTAL_SALES
FROM BOOK b inner join BOOK_SALES s
using(book_id)
where sales_date LIKE '2022-01-%'
GROUP BY CATEGORY
ORDER BY CATEGORY ASC
