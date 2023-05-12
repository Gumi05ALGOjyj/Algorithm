select concat('/home/grep/src/',file.Board_id,'/',file.file_id,file.file_name,file.file_ext) as FILE_PATH
from USED_GOODS_BOARD board ,USED_GOODS_FILE file
where board.board_id = file.board_id
and board.board_id = (select board_id
from USED_GOODS_BOARD
order by views desc
limit 1)
order by file.file_id desc;
