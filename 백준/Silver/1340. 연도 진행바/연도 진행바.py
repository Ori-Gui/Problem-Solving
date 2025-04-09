import sys
from datetime import datetime

def main():
    input_date = sys.stdin.readline().rstrip()

    # 날짜 형식 파싱
    date_format = "%B %d, %Y %H:%M"
    date = datetime.strptime(input_date, date_format)

    start = datetime(date.year, 1, 1)
    end = datetime(date.year + 1, 1, 1)

    # 연도의 총 길이, 현재까지의 길이 초 단위로 계산
    total_days = (end - start).total_seconds()
    elapsed_time = (date - start).total_seconds()

    p = (elapsed_time / total_days) * 100 # 백분율 계산

    print(p)
        
if __name__ == "__main__":
    main()
