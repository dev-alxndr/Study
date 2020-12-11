## References
- [How to add TailWind to HTML](https://themesberg.com/knowledge-center/tailwind-css/html)

```
npm install tailwindcss --save
```

- main.css
```
@tailwind base;

@tailwind components;

@tailwind utilities;
```

```
npm tailwindcss init
```

- tailwind.config.js
```
module.exports = {
    plugins: [
    // ...
    require('tailwindcss'),
    require('autoprefixer'),
    // ...
    ]
}
```

```
npx tailwindcss build main.css -o output.css
```

```HTML
    <link href=”./output.css” rel=”stylesheet”>
```



