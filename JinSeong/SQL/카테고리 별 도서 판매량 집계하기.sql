-- 코드를 입력하세요
SELECT CATEGORY, SUM(SALES) as "TOTAL_SALES"
FROM BOOK b, BOOK_SALES s 
where b.BOOK_ID = s.BOOK_ID and s.SALES_DATE BETWEEN date('2022-01-01') and date('2022-01-31')
group by CATEGORY  
order by CATEGORY;
