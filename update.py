#!/usr/bin/env python

import os
from urllib import parse

HEADER = """#
# 🎯 백준, 프로그래머스, SWEA 문제 풀이 목록
"""

# 백준 등급 이모지 매핑
BOJ_TIER_ORDER = {
    "Bronze": "🥉 Bronze",
    "Silver": "🥈 Silver",
    "Gold": "🥇 Gold",
    "Platinum": "💚 Platinum",
    "Diamond": "❤ Diamond",
    "Ruby": "❤️ Ruby"
}

# 프로그래머스 레벨 이모지 매핑
PROGRAMMERS_LEVEL = {
    "0": "🍼 Lv.0",
    "1": "🐣 Lv.1",
    "2": "🐥 Lv.2",
    "3": "🐤 Lv.3",
    "4": "🦉 Lv.4",
    "5": "🦅 Lv.5"
}

# SWEA 단계 이모지 매핑
def swea_label(name):
    return f"🌟 {name.upper()}"  # e.g., D1 → 🌟 D1

def main():
    content = ""
    content += HEADER
    
    written_titles = set()
    written_main_dirs = set()
    
    # 메인 카테고리 목록
    main_categories = ["백준", "프로그래머스", "SWEA"]

    for root, dirs, files in os.walk("."):
        dirs.sort()
        if root == '.':
            # .git, .github 같은 폴더는 무시
            for dir in ('.git', '.github'):
                try:
                    dirs.remove(dir)
                except ValueError:
                    pass
            continue

        current_dir = os.path.basename(root)
        parent = os.path.basename(os.path.dirname(root))

        # 1) 현재 디렉토리가 메인 카테고리인 경우 (예: 저장소 최상위에 "백준" 등)
        if current_dir in main_categories:
            main_category = current_dir
            if main_category not in written_main_dirs:
                content += f"## 📚 {main_category}\n"
                written_main_dirs.add(main_category)
            # 메인 폴더에 직접 파일이 있을 경우 목록화
            if files:
                title_key = f"{main_category}/(default)"
                if title_key not in written_titles:
                    content += f"### (default)\n"
                    content += "| 문제번호 & 문제명 | 링크 |\n"
                    content += "| ----- | ----- |\n"
                    written_titles.add(title_key)
                for file in files:
                    problem_id = os.path.splitext(file)[0]
                    file_path = os.path.join(root, file)
                    content += "|{}|[링크]({})|\n".format(problem_id, parse.quote(file_path))
            continue

        # 2) 현재 디렉토리가 메인 카테고리의 하위 디렉토리인 경우
        if parent in main_categories:
            main_category = parent
            if main_category not in written_main_dirs:
                content += f"## 📚 {main_category}\n"
                written_main_dirs.add(main_category)
            
            # 하위 디렉토리에 해당하는 타이틀 생성
            if main_category == "백준":
                tier_title = BOJ_TIER_ORDER.get(current_dir, f"✅ {current_dir}")
            elif main_category == "프로그래머스":
                tier_title = PROGRAMMERS_LEVEL.get(current_dir, f"📘 Lv.{current_dir}")
            elif main_category == "SWEA":
                tier_title = swea_label(current_dir)
            else:
                tier_title = f"☑️ {current_dir}"
            
            title_key = f"{main_category}/{current_dir}"
            if title_key not in written_titles:
                content += f"### {tier_title}\n"
                content += "| 문제번호 & 문제명 | 링크 |\n"
                content += "| ----- | ----- |\n"
                written_titles.add(title_key)

            # 해당 하위 디렉토리의 문제 파일들을 추가
            for file in files:
                problem_id = os.path.splitext(file)[0]
                file_path = os.path.join(root, file)
                content += "|{}|[링크]({})|\n".format(problem_id, parse.quote(file_path))
        else:
            # 메인 카테고리와 관계 없는 디렉토리는 무시
            continue

    with open("README.md", "w", encoding="utf-8") as fd:
        fd.write(content)

if __name__ == "__main__":
    main()
