window.addEventListener("DOMContentLoaded", function() {
  const toggleDarkMode = document.getElementById("theme-toggle");

  if (localStorage.getItem('theme') === null) {
    document.documentElement.classList.add('dark-mode');
    document.documentElement.classList.remove('light-mode');
    localStorage.setItem('theme', 'dark');
  } else {
    if (localStorage.getItem('theme') === 'light') {
      setTheme('light');
    } else {
      setTheme('dark');
    }  
  }

  jtd.addEvent(toggleDarkMode, 'click', function(){
    const currentTheme = getTheme();
    const newTheme = currentTheme === 'light' ? 'dark' : 'light';

    localStorage.setItem('theme', newTheme);
    setTheme(newTheme);
  });

  function getTheme() {
    return document.documentElement.classList.contains('dark-mode') ? 'dark' : 'light';
  }

  function setTheme(theme) {
    document.documentElement.classList.add("transition");
    window.setTimeout(() => {
      document.documentElement.classList.remove("transition");
    }, 500)

    if (theme === 'dark') {
      toggleDarkMode.innerHTML = `<svg width='18px' height='18px'><use href="#svg-moon"></use></svg>`;
      document.documentElement.classList.add('dark-mode');
      document.documentElement.classList.remove('light-mode');
    } else {
      toggleDarkMode.innerHTML = `<svg width='18px' height='18px'><use href="#svg-sun"></use></svg>`;
      document.documentElement.classList.add('light-mode');
      document.documentElement.classList.remove('dark-mode');
    }
  }
});
