package com.baohao.esimkeeper.data

object Countries {
    val common = listOf(
        CountryOption("中国大陆", "CN", "+86", "🇨🇳"),
        CountryOption("香港", "HK", "+852", "🇭🇰"),
        CountryOption("澳门", "MO", "+853", "🇲🇴"),
        CountryOption("台湾", "TW", "+886", "🇹🇼"),
        CountryOption("美国", "US", "+1", "🇺🇸"),
        CountryOption("加拿大", "CA", "+1", "🇨🇦"),
        CountryOption("英国", "GB", "+44", "🇬🇧"),
        CountryOption("法国", "FR", "+33", "🇫🇷"),
        CountryOption("德国", "DE", "+49", "🇩🇪"),
        CountryOption("意大利", "IT", "+39", "🇮🇹"),
        CountryOption("西班牙", "ES", "+34", "🇪🇸"),
        CountryOption("荷兰", "NL", "+31", "🇳🇱"),
        CountryOption("瑞士", "CH", "+41", "🇨🇭"),
        CountryOption("土耳其", "TR", "+90", "🇹🇷"),
        CountryOption("日本", "JP", "+81", "🇯🇵"),
        CountryOption("韩国", "KR", "+82", "🇰🇷"),
        CountryOption("新加坡", "SG", "+65", "🇸🇬"),
        CountryOption("马来西亚", "MY", "+60", "🇲🇾"),
        CountryOption("泰国", "TH", "+66", "🇹🇭"),
        CountryOption("越南", "VN", "+84", "🇻🇳"),
        CountryOption("菲律宾", "PH", "+63", "🇵🇭"),
        CountryOption("印度尼西亚", "ID", "+62", "🇮🇩"),
        CountryOption("印度", "IN", "+91", "🇮🇳"),
        CountryOption("阿联酋", "AE", "+971", "🇦🇪"),
        CountryOption("沙特阿拉伯", "SA", "+966", "🇸🇦"),
        CountryOption("澳大利亚", "AU", "+61", "🇦🇺"),
        CountryOption("新西兰", "NZ", "+64", "🇳🇿"),
        CountryOption("墨西哥", "MX", "+52", "🇲🇽"),
        CountryOption("巴西", "BR", "+55", "🇧🇷"),
    )

    fun search(query: String): List<CountryOption> {
        val keyword = query.trim()
        if (keyword.isBlank()) return common
        return common.filter { option ->
            option.countryName.contains(keyword, ignoreCase = true) ||
                option.countryCode.contains(keyword, ignoreCase = true) ||
                option.dialCode.contains(keyword)
        }
    }

    fun findByIso(countryIso: String): CountryOption? {
        val normalized = countryIso.trim().uppercase()
        return common.firstOrNull { it.countryCode == normalized }
    }
}
