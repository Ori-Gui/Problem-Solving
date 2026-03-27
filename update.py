# update.py
#!/usr/bin/env python3

import os
from urllib import parse

README_PATH = "README.md"
START_MARKER = "<!-- AA-README-START -->"
END_MARKER = "<!-- AA-README-END -->"

GENERATED_HEADER = """
## 📚 Problem Solving
"""

BOJ_TIER_ORDER = {
    "Bronze": "🥉 Bronze",
    "Silver": "🥈 Silver",
    "Gold": "🥇 Gold",
    "Platinum": "🍏 Platinum",
    "Diamond": "💎 Diamond",
    "Ruby": "❤️ Ruby"
}

PROGRAMMERS_LEVEL = {
    "0": "🍼 Lv.0",
    "1": "🐣 Lv.1",
    "2": "🐥 Lv.2",
    "3": "🐤 Lv.3",
    "4": "🦉 Lv.4",
    "5": "🦅 Lv.5"
}


def swea_label(name: str) -> str:
    return f"⭐ {name.upper()}"


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
            return int(folder_name.split(".")[0].strip())
        except ValueError:
            return float("inf")
    return float("inf")


def build_generated_content() -> str:
    content = GENERATED_HEADER + "\n"

    main_categories = ["백준", "프로그래머스", "SWEA"]
    data = {}

    for root, dirs, files in os.walk("."):
        dirs[:] = [d for d in dirs if d not in (".git", ".github", "images")]

        if root == ".":
            continue

        main_cat = None
        for cat in main_categories:
            if os.sep + cat + os.sep in root or root.endswith(os.sep + cat) or root == "./" + cat:
                main_cat = cat
                break

        if main_cat is None:
            continue

        main_path = os.path.join(".", main_cat)
        rel_path = os.path.relpath(root, main_path)
        parts = rel_path.split(os.sep)
        sub_cat = parts[0]

        problem_folder = None
        if len(parts) > 1:
            problem_folder = parts[1]

        if (main_cat, sub_cat) not in data:
            data[(main_cat, sub_cat)] = {}

        if problem_folder is not None:
            if problem_folder not in data[(main_cat, sub_cat)]:
                data[(main_cat, sub_cat)][problem_folder] = []

            for file in files:
                if file.lower() == "readme.md":
                    continue
                full_path = os.path.join(root, file)
                data[(main_cat, sub_cat)][problem_folder].append(full_path)

    for main_cat in main_categories:
        keys = [(k, v) for k, v in data.items() if k[0] == main_cat]
        if not keys:
            continue

        content += "---\n"
        content += f"## 📚 {main_cat}\n"

        if main_cat == "백준":
            order = ["Bronze", "Silver", "Gold", "Platinum", "Diamond", "Ruby"]
            keys_sorted = sorted(
                keys,
                key=lambda item: order.index(item[0][1]) if item[0][1] in order else 999
            )
        else:
            keys_sorted = sorted(keys, key=lambda x: x[0][1])

        for (mc, sub_cat), problem_map in keys_sorted:
            if sub_cat == ".":
                continue

            if mc == "백준":
                tier_title = BOJ_TIER_ORDER.get(sub_cat, f"✅ {sub_cat}")
            elif mc == "프로그래머스":
                tier_title = PROGRAMMERS_LEVEL.get(sub_cat, f"📘 Lv.{sub_cat}")
            elif mc == "SWEA":
                tier_title = swea_label(sub_cat)
            else:
                tier_title = sub_cat

            content += f"### {tier_title}\n"
            content += "| 문제 | 링크 |\n"
            content += "| ----- | ---- |\n"

            for pfolder, _ in sorted(problem_map.items(), key=lambda item: extract_problem_number(item[0])):
                parsed_name = parse_problem_folder(pfolder)
                folder_path = os.path.join(".", mc, sub_cat, pfolder)
                content += f"| {parsed_name} | [링크]({parse.quote(folder_path)}) |\n"

            content += "\n"

    return content.strip() + "\n"


def update_readme():
    generated = build_generated_content()

    if os.path.exists(README_PATH):
        with open(README_PATH, "r", encoding="utf-8") as f:
            readme = f.read()
    else:
        readme = ""

    block = f"{START_MARKER}\n{generated}{END_MARKER}"

    if START_MARKER in readme and END_MARKER in readme:
        start = readme.index(START_MARKER)
        end = readme.index(END_MARKER) + len(END_MARKER)
        updated = readme[:start] + block + readme[end:]
    else:
        prefix = readme.strip()
        if prefix:
            updated = prefix + "\n\n---\n\n" + block + "\n"
        else:
            updated = block + "\n"

    with open(README_PATH, "w", encoding="utf-8") as f:
        f.write(updated)


if __name__ == "__main__":
    update_readme()
