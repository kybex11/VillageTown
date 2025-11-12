import React, { useEffect, useState } from "react";
import { Canvas, useFrame } from "@react-three/fiber";
import { OrbitControls } from "@react-three/drei";

function InteractiveCube() {
  const meshRef = React.useRef();
  const [mouse, setMouse] = useState({ x: 0, y: 0 });

  useEffect(() => {
    const handleMouseMove = (e) => {
      setMouse({
        x: (e.clientX / window.innerWidth - 0.5) * 2,
        y: (e.clientY / window.innerHeight - 0.5) * 2,
      });
    };
    window.addEventListener("mousemove", handleMouseMove);
    return () => window.removeEventListener("mousemove", handleMouseMove);
  }, []);

  useFrame(() => {
    if (meshRef.current) {
      meshRef.current.rotation.y += 0.01 + mouse.x * 0.02;
      meshRef.current.rotation.x += 0.01 - mouse.y * 0.02;
    }
  });

  return (
    <mesh ref={meshRef}>
      <boxGeometry args={[2.5, 2.5, 2.5]} />
      <meshStandardMaterial color="#ffd166" />
    </mesh>
  );
}

export default function App() {
  const fullTitle = "Village Town";
  const [displayed, setDisplayed] = useState("");

  useEffect(() => {
    let index = 0;
    let forward = true;
    const interval = setInterval(() => {
      if (forward) {
        setDisplayed(fullTitle.slice(0, index + 1));
        index++;
        if (index >= fullTitle.length) {
          forward = false;
        }
      } else {
        index--;
        setDisplayed(fullTitle.slice(0, index + 1));
        if (index <= 0) forward = true;
      }
    }, 150);
    return () => clearInterval(interval);
  }, []);

  return (
    <div className="vt-root">
      <link
        href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap"
        rel="stylesheet"
      />
      <style>{`
:root{
  --bg-1: #7da23f;
  --bg-2: #5b8030;
  --dirt: #6b4f2f;
  --wood: #b07a3a;
  --accent: #ffd166;
  --text: #0b1a0f;
  --panel: rgba(255,255,255,0.06);
  --glass: rgba(255,255,255,0.08);
  --hover-glow: rgba(255,255,255,0.2);
  --footer-bg: rgba(107, 79, 47, 0.85);
}
*{box-sizing:border-box;margin:0;padding:0}
html,body,#root{height:100%}
body{
  font-family:'Press Start 2P', cursive, Arial, sans-serif;
    background:
    repeating-linear-gradient(
      45deg,
      rgba(255,255,255,0.05) 0 10px,
      transparent 10px 20px
    ),
    linear-gradient(180deg, var(--bg-1) 0%, var(--bg-2) 100%);
      background-attachment: fixed;
  background-size: auto;
  color:var(--text);
  overflow-x:hidden;
  position:relative;
}
body::before{
  content:"";
  position:absolute;
  top:0;left:0;width:100%;height:100%;
  background-image: repeating-linear-gradient(
    45deg,
    rgba(255,255,255,0.05) 0 10px,
    transparent 10px 20px
  );
  z-index:0;
}
.vt-header{position:relative;z-index:10;display:flex;align-items:center;justify-content:space-between;padding:32px 48px;background:var(--panel);backdrop-filter:blur(6px);border-bottom:5px solid var(--dirt)}
.vt-title{
  font-size:42px;
  color:var(--accent);
  text-shadow:4px 4px 0 rgba(0,0,0,0.35);
  white-space:nowrap;
  overflow:hidden;
}
.cursor{
  display:inline-block;
  width:10px;
  background:var(--accent);
  animation: blink 0.7s infinite;
}
@keyframes blink{
  0%,50%,100%{opacity:1}
  25%,75%{opacity:0}
}
.vt-nav{display:flex;gap:20px}
.vt-nav a{padding:12px 18px;border-radius:8px;text-decoration:none;color:var(--text);font-weight:bold;transition:0.3s}
.vt-nav a:hover{background:var(--hover-glow);transform:translateY(-2px)}
.vt-hero{padding:80px 48px;display:flex;gap:40px;align-items:center;animation:fadeIn 1s ease-out;position:relative;z-index:5}
.vt-hero-left{flex:1;position:relative;z-index:5;}
.vt-hero h1{font-size:48px;line-height:1.1;margin-bottom:20px;color:var(--accent);text-shadow:3px 3px 0 #000;overflow:hidden}
.vt-hero p{font-size:18px;max-width:70ch;margin-bottom:24px;opacity:0.95}
.vt-cta{display:flex;gap:16px;flex-wrap:wrap;}
.vt-btn{
  background:linear-gradient(180deg,var(--wood),#8a5b33);
  padding:14px 20px;
  border-radius:10px;
  color:white;
  font-weight:bold;
  border:3px dashed rgba(0,0,0,0.25);
  box-shadow:0 6px 0 rgba(0,0,0,0.15);
  text-decoration:none;
  transition:all 0.3s;
  font-family:'Press Start 2P', cursive;
}
.vt-btn:hover{transform:translateY(-3px) scale(1.05);box-shadow:0 10px 0 rgba(0,0,0,0.2)}
.vt-btn.secondary{background:transparent;color:var(--text);border:3px dashed rgba(255,255,255,0.15)}
.vt-mock{
  width:500px;
  height:380px;
  border-radius:16px;
  background: linear-gradient(180deg,#5b8030,#3e5b21);
  box-shadow:10px 12px 0 rgba(0,0,0,0.15);
  display:flex;
  flex-direction:column;
  padding:20px;
  border:6px solid var(--dirt);
  animation:slideIn 1s ease-out;
}
.vt-mock canvas{border-radius:10px}
.vt-mock .info{display:flex;gap:10px;margin-top:12px}
.vt-mini{flex:1;background:var(--panel);padding:10px;border-radius:8px;font-size:12px}
.vt-features{display:grid;grid-template-columns:repeat(auto-fit,minmax(240px,1fr));gap:20px;padding:24px 48px;animation:fadeIn 1.5s ease-out}
.feat{background:linear-gradient(180deg,#6aa84f,#4a7027);padding:20px;border-radius:14px;border:3px dashed rgba(0,0,0,0.15);box-shadow:0 6px 0 rgba(0,0,0,0.06);transition:all 0.3s}
.feat:hover{transform:translateY(-4px) scale(1.03);box-shadow:0 10px 0 rgba(0,0,0,0.1)}
.feat h3{margin-bottom:10px;font-size:20px;color:#ffd166}
.feat p{font-size:15px;opacity:0.9;color:#fff8d2}
.building-card{
  background: linear-gradient(180deg,#7da23f,#5b8030);
  padding:14px;
  border-radius:10px;
  display:flex;
  gap:14px;
  align-items:center;
  border:3px dashed rgba(0,0,0,0.15);
  transition:all 0.3s;
}
.building-card:hover{transform:translateY(-2px)}
.building-icon{
  width:70px;
  height:70px;
  border-radius:4px;
  background:#ffd166;
  display:grid;
  place-items:center;
  font-weight:900;
  box-shadow:2px 4px 0 rgba(0,0,0,0.12);
  font-size:28px;
  image-rendering: pixelated;
}
.vt-footer{
  margin-top:36px;
  padding:28px 48px;
  display:flex;
  justify-content:space-between;
  align-items:center;
  font-size:14px;
  opacity:0.95;
  background:var(--footer-bg);
  border-top:4px solid var(--dirt);
  backdrop-filter:blur(4px);
  animation:fadeIn 2s ease-out;
}
@keyframes fadeIn{from{opacity:0}to{opacity:1}}
@keyframes slideIn{from{opacity:0;transform:translateX(60px)}to{opacity:1;transform:translateX(0)}}
@media (max-width:900px){
  .vt-hero{flex-direction:column;align-items:stretch}
  .vt-mock{width:100%}
  .vt-hero h1{font-size:36px}
}

@media (max-width:900px){
  .vt-hero{
    flex-direction:column;
    align-items:stretch;
    padding:40px 24px;
  }
  .vt-hero-left{
    text-align:center;
  }
  .vt-mock{
    width:100%;
    height:300px;
    margin-top:24px;
  }
  .vt-hero h1{
    font-size:28px;
    margin-bottom:16px;
  }
  .vt-hero p{
    font-size:14px;
  }
  .vt-cta{
    justify-content:center;
    flex-wrap:wrap;
    gap:10px;
  }
  .vt-features{
    padding:24px 16px;
  }
  .feat h3{
    font-size:18px;
  }
  .feat p{
    font-size:13px;
  }
  .building-card{
    flex-direction:column;
    align-items:center;
    text-align:center;
  }
  .building-icon{
    width:50px;
    height:50px;
    font-size:22px;
    margin-bottom:6px;
  }
  #buildings h2, #download h2{
    font-size:22px;
  }
  #download p{
    font-size:14px;
  }
  .vt-footer{
    flex-direction:column;
    gap:6px;
    text-align:center;
    font-size:12px;
    padding:16px 24px;
  }
  .vt-nav{
    flex-wrap:wrap;
    gap:10px;
  }
}

      `}</style>

      <header className="vt-header">
        <div className="vt-title">{displayed}<span className="cursor"></span></div>
        <nav className="vt-nav">
          <a href="#features">Особенности</a>
          <a href="#buildings">Постройки</a>
          <a href="#download">Скачать</a>
        </nav>
      </header>

      <main>
        <section className="vt-hero">
          <div className="vt-hero-left">
            <h1>{displayed}<span className="cursor"></span></h1>
            <p>Управляйте посёлком, стройте постройки, наблюдайте за NPC и развивайте экономику. Мод вдохновлён MineColonies, но предлагает уникальные возможности кастомизации.</p>
            <div className="vt-cta">
              <a className="vt-btn" href="#download">Скачать мод</a>
              <a className="vt-btn secondary" href="#features">Узнать больше</a>
            </div>
          </div>

          <aside className="vt-mock">
            <Canvas camera={{ position: [5, 5, 8], fov: 50 }}>
              <ambientLight intensity={0.6} />
              <directionalLight position={[5, 10, 7]} intensity={0.8} />
              <InteractiveCube />
              <OrbitControls enablePan={false} enableZoom={false} />
            </Canvas>

            <div className="info">
              <div className="vt-mini">Версия: 1.0.0</div>
              <div className="vt-mini">Совместимость: Fabric/Forge</div>
            </div>
          </aside>
        </section>

        <section id="features" className="vt-features">
          {[{title:'Экономика и торговля',desc:'Создавайте рынки, назначайте торговцев, настраивайте цены в зависимости от спроса.'},
            {title:'Постройки и крафт',desc:'Каждая постройка даёт бонусы посёлку и открывает новые профессии.'},
            {title:'AI жителей',desc:'NPC выполняют задания, ищут работу, заботятся о семье и реагируют на события.'},
            {title:'Интеграция модов',desc:'Поддержка популярных API и лёгкая интеграция с другими модами.'}].map(f => (
            <div key={f.title} className="feat">
              <h3>{f.title}</h3>
              <p>{f.desc}</p>
            </div>
          ))}
        </section>

        <section id="buildings" style={{ padding: '0 36px 18px' }}>
          <h2 style={{ margin:'8px 0 14px' }}>Популярные постройки</h2>
          <div style={{ display:'grid', gridTemplateColumns:'repeat(auto-fit,minmax(240px,1fr))', gap:14 }}>
            {[{title:'Ферма',desc:'Автосбор урожая и распределение по складу'},
              {title:'Кузница',desc:'Производство инструментов и улучшений'},
              {title:'Рынок',desc:'Торговля с жителями и путешественниками'},
              {title:'Обсерватория',desc:'Повышает культуру и знания'}].map(b => (
              <div key={b.title} className="feat">
                <div className="building-card" style={{ marginBottom:8 }}>
                  <div className="building-icon">🪓</div>
                  <div>
                    <strong>{b.title}</strong>
                    <div style={{ fontSize:13 }}>{b.desc}</div>
                  </div>
                </div>
                <a className="vt-btn" href="#">Открыть схему</a>
              </div>
            ))}
          </div>
        </section>

        <section id="download" style={{ padding:'24px 36px' }}>
          <h2>Скачать</h2>
          <p style={{ maxWidth:720 }}>Последняя стабильная версия доступна на странице проекта. Выберите сборку под вашу платформу (Fabric/Forge) и следуйте инструкциям по установке.</p>
          <div style={{ marginTop:12, display:'flex', gap:12 }}>
            <a className="vt-btn" href="#">Скачать Fabric</a>
            <a className="vt-btn" href="#">Скачать Forge</a>
          </div>
        </section>
      </main>

      <footer className="vt-footer">
        <div>© Village Town — мод для Minecraft • Сделано с любовью к блокам</div>
        <div>Discord • GitHub • Документация</div>
      </footer>
    </div>
  );
}
