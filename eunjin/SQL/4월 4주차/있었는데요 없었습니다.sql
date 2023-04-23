-- 코드를 입력하세요
SELECT ins.ANIMAL_ID, ins.NAME
FROM ANIMAL_INS ins, ANIMAL_OUTS outs
where ins.ANIMAL_ID = outs.ANIMAL_ID and ins.DATETIME>outs.DATETIME and outs.DATETIME is not null
order by ins.DATETIME;
