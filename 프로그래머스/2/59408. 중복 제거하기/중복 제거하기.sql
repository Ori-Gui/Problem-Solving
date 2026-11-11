-- 코드를 입력하세요
SELECT COUNT(DISTINCT name) as 'count'
FROM animal_ins
WHERE NAME IS NOT NULL;