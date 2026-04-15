#!/usr/bin/env python3

import os
import re
from urllib import parse

README_PATH = "README.md"

START_MARKER = "<" + "!-- AA-README-START --" + ">"
END_MARKER = "<" + "!-- AA-README-END --" + ">"

PLATFORM_TITLES = {
    "백준": "📗 백준",
    "프로그래머스": "📕 프로그래머스",
    "SWEA": "📘 SWEA",
    "goormlevel": "📙 구름레벨",
}

BOJ_TIER_ORDER = [
    "Bronze V", "Bronze IV", "Bronze III", "Bronze II", "Bronze I",
    "Silver V", "Silver IV", "Silver III", "Silver II", "Silver I",
    "Gold V", "Gold IV", "Gold III", "Gold II", "Gold I",
    "Platinum V", "Platinum IV", "Platinum III", "Platinum II", "Platinum I",
    "Diamond V", "Diamond IV", "Diamond III", "Diamond II", "Diamond I",
    "Ruby V", "Ruby IV", "Ruby III", "Ruby II", "Ruby I",
    "Unrated",
]

BOJ_TIER_LABELS = {
    "Bronze V": "🟤 Bronze V", "Bronze IV": "🟤 Bronze IV", "Bronze III": "🟤 Bronze III", "Bronze II": "🟤 Bronze II", "Bronze I": "🟤 Bronze I",
    "Silver V": "⚪ Silver V", "Silver IV": "⚪ Silver IV", "Silver III": "⚪ Silver III", "Silver II": "⚪ Silver II", "Silver I": "⚪ Silver I",
    "Gold V": "🟡 Gold V", "Gold IV": "🟡 Gold IV", "Gold III": "🟡 Gold III", "Gold II": "🟡 Gold II", "Gold I": "🟡 Gold I",
    "Platinum V": "🟢 Platinum V", "Platinum IV": "🟢 Platinum IV", "Platinum III": "🟢 Platinum III", "Platinum II": "🟢 Platinum II", "Platinum I": "🟢 Platinum I",
    "Diamond V": "🔵 Diamond V", "Diamond IV": "🔵 Diamond IV", "Diamond III": "🔵 Diamond III", "Diamond II": "🔵 Diamond II", "Diamond I": "🔵 Diamond I",
    "Ruby V": "🔴 Ruby V", "Ruby IV": "🔴 Ruby IV", "Ruby III": "🔴 Ruby III", "Ruby II": "🔴 Ruby II", "Ruby I": "🔴 Ruby I",
    "Unrated": "◼️ Unrated",
}

def get_platform_title(main_cat: str) -> str:
    return PLATFORM_TITLES.get(main_cat, main_cat)

def parse_problem_folder(folder_name: str) -> str:
    if "." in folder_name:
        parts = folder_name.split(".", 1)
        prob_num = parts[0].strip()
        prob_title = parts[1].strip()
        return f"{prob_num} - {prob_title}"
    return folder_name

def extract_problem_number(folder_name: str):
    if "." in folder_name:
        try:
            return int(folder_name.split(".", 1)[0].strip())
        except ValueError:
            return float("inf")
    return float("inf")

def get_tier_title(main_cat: str, sub_cat: str) -> str:
    if main_cat == "백준":
        return BOJ_TIER_LABELS.get(sub_cat, f"◻️ {sub_cat}")
    
    if main_cat == "goormlevel":
        if sub_cat.strip().lower() in ["undefined", "unrated"]:
            return "☁️ Level"
        return f"☁️ Lv.{sub_cat}"
        
    if sub_cat.strip().lower() == "unrated":
        return "◼️ Unrated"
    if main_cat == "프로그래머스":
        return f"🔶 Lv.{sub_cat}"
    if main_cat == "SWEA":
        return f"🔷 {sub_cat.upper()}"
    return sub_cat

def extract_boj_tier_from_readme(readme_path: str) -> str:
    if not os.path.exists(readme_path):
        return "Unrated"
    try:
        with open(readme_path, "r", encoding="utf-8") as f:
            content = f.read()
    except Exception:
        try:
            with open(readme_path, "r", encoding="utf-8-sig") as f:
                content = f.read()
        except Exception:
            return "Unrated"

    patterns = [
        r"\[(Bronze|Silver|Gold|Platinum|Diamond|Ruby)\s+(I|II|III|IV|V)\]",
        r"(Bronze|Silver|Gold|Platinum|Diamond|Ruby)\s+(I|II|III|IV|V)",
    ]
    for pattern in patterns:
        match = re.search(pattern, content, re.IGNORECASE)
        if match:
            return f"{match.group(1).capitalize()} {match.group(2).upper()}"
    return "Unrated"

def build_boj_data():
    data = {}
    boj_root = os.path.join(".", "백준")
    if not os.path.exists(boj_root): return data

    for major_dir in os.listdir(boj_root):
        major_path = os.path.join(boj_root, major_dir)
        if not os.path.isdir(major_path): continue
        for problem_folder in os.listdir(major_path):
            problem_path = os.path.join(major_path, problem_folder)
            if not os.path.isdir(problem_path): continue
            
            readme_path = os.path.join(problem_path, "README.md")
            tier = extract_boj_tier_from_readme(readme_path)
            
            if tier not in data: data[tier] = []
            data[tier].append({"folder": problem_folder, "path": problem_path})
    return data

def build_other_platform_data(main_cat: str):
    data = {}
    root_path = os.path.join(".", main_cat)
    if not os.path.exists(root_path): return data

    for sub_cat in os.listdir(root_path):
        sub_cat_path = os.path.join(root_path, sub_cat)
        if not os.path.isdir(sub_cat_path): continue
        if sub_cat not in data: data[sub_cat] = []
        for problem_folder in os.listdir(sub_cat_path):
            problem_path = os.path.join(sub_cat_path, problem_folder)
            if os.path.isdir(problem_path):
                data[sub_cat].append({"folder": problem_folder, "path": problem_path})
    return data

def sort_programmers_key(value: str):
    try: return (0, int(value))
    except ValueError: return (1, value)

def sort_swea_key(value: str):
    order = ["D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "Unrated"]
    try: return order.index(value.upper())
    except ValueError: return 999

def sort_goorm_key(value: str):
    if value.lower() in ["undefined", "unrated"]:
        return (999, value)
    try: return (0, int(value))
    except ValueError: return (1, value)

def render_problem_table(problem_items: list[dict]) -> str:
    lines = ["\n| 문제 | 링크 |\n| ----- | ---- |"]
    for item in sorted(problem_items, key=lambda x: extract_problem_number(x["folder"])):
        parsed_name = parse_problem_folder(item["folder"])
        link = parse.quote(item["path"], safe="/")
        lines.append(f"| {parsed_name} | [링크]({link}) |")
    lines.append("")
    return "\n".join(lines)

def render_tier_details(main_cat: str, sub_cat: str, problem_items: list[dict]) -> str:
    title = get_tier_title(main_cat, sub_cat)
    return f"<details>\n<summary><b>{title}</b> ({len(problem_items)})</summary>\n{render_problem_table(problem_items)}\n</details>\n"

def render_platform_section(main_cat: str, grouped_data: dict, sorted_keys: list[str]) -> str:
    total_count = sum(len(grouped_data[key]) for key in sorted_keys if key in grouped_data)
    title = get_platform_title(main_cat)
    lines = ["---", f"### {title} ({total_count})\n"]
    for key in sorted_keys:
        if grouped_data.get(key):
            lines.append(render_tier_details(main_cat, key, grouped_data[key]))
    return "\n".join(lines)

def build_generated_content() -> str:
    parts = []

    # 1) 백준
    boj_data = build_boj_data()
    if boj_data:
        sorted_boj_keys = sorted(boj_data.keys(), key=lambda x: BOJ_TIER_ORDER.index(x) if x in BOJ_TIER_ORDER else 999)
        parts.append(render_platform_section("백준", boj_data, sorted_boj_keys))

    # 2) 프로그래머스
    programmers_data = build_other_platform_data("프로그래머스")
    if programmers_data:
        parts.append(render_platform_section("프로그래머스", programmers_data, sorted(programmers_data.keys(), key=sort_programmers_key)))

    # 3) SWEA
    swea_data = build_other_platform_data("SWEA")
    if swea_data:
        parts.append(render_platform_section("SWEA", swea_data, sorted(swea_data.keys(), key=sort_swea_key)))

    # 4) 구름레벨
    goorm_data = build_other_platform_data("goormlevel")
    if goorm_data:
        sorted_goorm_keys = sorted(goorm_data.keys(), key=sort_goorm_key)
        parts.append(render_platform_section("goormlevel", goorm_data, sorted_goorm_keys))

    return "\n".join(parts).strip() + "\n"

def update_readme():
    generated = build_generated_content()
    readme = ""
    if os.path.exists(README_PATH):
        with open(README_PATH, "r", encoding="utf-8") as f:
            readme = f.read()

    block = f"{START_MARKER}\n{generated}\n{END_MARKER}"

    if START_MARKER in readme and END_MARKER in readme:
        start_idx = readme.find(START_MARKER)
        end_idx = readme.find(END_MARKER) + len(END_MARKER)
        updated = readme[:start_idx] + block + readme[end_idx:]
    else:
        if readme.strip():
            updated = readme.rstrip() + "\n\n" + block + "\n"
        else:
            updated = block + "\n"

    with open(README_PATH, "w", encoding="utf-8") as f:
        f.write(updated)

if __name__ == "__main__":
    update_readme()
