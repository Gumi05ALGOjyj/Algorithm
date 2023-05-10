-- 코드를 입력하세요
SELECT * from PLACES 
where host_id in (select host_id from PLACES  group by host_id having count(id)>1)
order by id;
