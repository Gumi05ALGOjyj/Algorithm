-- 코드를 입력하세요
SELECT CONCAT("/home/grep/src/",f.BOARD_ID,"/",f.FILE_ID,f.FILE_NAME,f.FILE_EXT) as FILE_PATH
FROM USED_GOODS_BOARD b join USED_GOODS_FILE f
on b.BOARD_ID = f.BOARD_ID
where b.VIEWS = (SELECT max(VIEWS) from USED_GOODS_BOARD)
order by f.FILE_ID desc;
