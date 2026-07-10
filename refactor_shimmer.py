import os

directory = "app/src/main/java/uk/usedcars/marketplace/dealers/auto/finance/presentation/ui/screens"
import_str = "import uk.usedcars.marketplace.dealers.auto.finance.presentation.ui.components.ShimmerAsyncImage\n"

for root, dirs, files in os.walk(directory):
    for file in files:
        if file.endswith(".kt"):
            filepath = os.path.join(root, file)
            with open(filepath, "r") as f:
                content = f.read()
            
            if "AsyncImage(" in content or "AsyncImage" in content:
                content = content.replace("import coil.compose.AsyncImage\n", "")
                
                # Insert ShimmerAsyncImage import after the first import or package declaration
                if import_str not in content:
                    lines = content.split('\n')
                    for i, line in enumerate(lines):
                        if line.startswith("import "):
                            lines.insert(i, import_str.strip())
                            break
                    content = '\n'.join(lines)
                
                content = content.replace("AsyncImage(", "ShimmerAsyncImage(")
                
                with open(filepath, "w") as f:
                    f.write(content)
                print(f"Refactored {filepath}")

