import re

with open('app/src/main/java/uk/usedcars/marketplace/dealers/auto/finance/domain/model/AppConfig.kt', 'r') as f:
    content = f.read()

# Replace imageUrl with imageUrls list
content = content.replace(
    '@SerializedName("image_url") val imageUrl: String,',
    '@SerializedName("image_urls") val imageUrls: List<String> = emptyList(),'
)

with open('app/src/main/java/uk/usedcars/marketplace/dealers/auto/finance/domain/model/AppConfig.kt', 'w') as f:
    f.write(content)
